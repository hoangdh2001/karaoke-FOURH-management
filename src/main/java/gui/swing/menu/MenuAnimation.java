package gui.swing.menu;

import java.awt.Component;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MenuAnimation {

    private final MigLayout layout;
    private final MenuItem menuItem;
    private Animator animator;
    private boolean open;

    public MenuAnimation(MigLayout layout, Component component, int height) {
        this.layout = layout;
        this.menuItem = (MenuItem) component;
        initAnimator(component, 200, height);
    }

    public MenuAnimation(MigLayout layout, Component component, int duration, int height) {
        this.layout = layout;
        this.menuItem = (MenuItem) component;
        initAnimator(component, duration, height);
    }

    private void initAnimator(Component component, int duration, int heightf) {
        int height = component.getPreferredSize().height;
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float h;
                if (open) {
                    h = heightf + ((height - heightf) * fraction);
                    menuItem.setAlpha(fraction);
                } else {
                    h = heightf + ((height - heightf) * (1f - fraction));
                    menuItem.setAlpha(1f - fraction);
                }
                layout.setComponentConstraints(menuItem, "h " + h + "!");
                component.revalidate();
                component.repaint();
            }
        };
        animator = new Animator(duration, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
    }

    public void openMenu() {
        open = true;
        animator.start();
    }

    public void closeMenu() {
        open = false;
        animator.start();
    }
}
