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
import javax.swing.JToggleButton;

/**
 * カードレイアウトに格納されるコンポーネントと対応するトグルボタンのペアです.
 *
 * @author desktopgame
 */
public class Slot {

    public final String name;
    public final JToggleButton toggleButton;
    public final Component tool;

    public Slot(String name, JToggleButton toggleButton, Component tool) {
        this.name = name;
        this.toggleButton = toggleButton;
        this.tool = tool;
    }

}
