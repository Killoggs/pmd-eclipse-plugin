/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.eclipse.ui.preferences.editors;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Spinner;

import net.sourceforge.pmd.eclipse.ui.preferences.br.SizeChangeListener;
import net.sourceforge.pmd.eclipse.ui.preferences.br.ValueChangeListener;
import net.sourceforge.pmd.properties.DoubleProperty;
import net.sourceforge.pmd.properties.NumericPropertyDescriptor;
import net.sourceforge.pmd.properties.PropertyDescriptor;
import net.sourceforge.pmd.properties.PropertySource;

/**
 * @author Brian Remedios
 */
public class DoubleEditorFactory extends AbstractRealNumberEditor<Double> {

    public static final DoubleEditorFactory INSTANCE = new DoubleEditorFactory();


    private DoubleEditorFactory() { }


    public PropertyDescriptor<Double> createDescriptor(String name, String description, Control[] otherData) {

        return new DoubleProperty(
            name,
            description,
            defaultIn(otherData).doubleValue(),
            minimumIn(otherData).doubleValue(),
            maximumIn(otherData).doubleValue(),
            0.0f
        );
    }


    protected Double valueFrom(Control valueControl) {

        return ((Spinner) valueControl).getSelection() / SCALE;
    }


    public Control newEditorOn(Composite parent, final PropertyDescriptor<Double> desc, final PropertySource source,
                               final ValueChangeListener listener, SizeChangeListener sizeListener) {

        final Spinner spinner = newSpinnerFor(parent, source, (NumericPropertyDescriptor<Double>) desc);

        spinner.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent event) {
                Double newValue = spinner.getSelection() / SCALE;
                if (newValue.equals(valueFor(source, desc))) {
                    return;
                }

                source.setProperty(desc, newValue);
                listener.changed(source, desc, newValue);

                adjustRendering(source, desc, spinner);
            }
        });

        return spinner;
    }
}
