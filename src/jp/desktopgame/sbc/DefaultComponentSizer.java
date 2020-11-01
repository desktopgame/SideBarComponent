/*
 * SideBarComponent
 *
 * Copyright (c) 2020 desktopgame
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package jp.desktopgame.sbc;

import java.awt.Component;
import javax.swing.JScrollPane;

/**
 *
 * @author desktopgame
 */
public class DefaultComponentSizer implements ComponentSizer {

    @Override
    public int computeWidth(String name, Component c) {
        if (c instanceof JScrollPane) {
            return ((JScrollPane) c).getViewport().getView().getPreferredSize().width;
        }
        return c.getPreferredSize().width;
    }

    @Override
    public int computeHeight(String name, Component c) {
        if (c instanceof JScrollPane) {
            return ((JScrollPane) c).getViewport().getView().getPreferredSize().height;
        }
        return c.getPreferredSize().height;
    }

}
