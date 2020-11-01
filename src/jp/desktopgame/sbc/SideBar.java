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
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JSplitPane;

/**
 * ボタンによって切り替え可能なカードレイアウトとずっと表示されているメインコンポーネントを格納するサイドバーです.
 *
 * @author desktopgame
 */
public class SideBar extends JComponent {

    public static final int HORIZONTAL = JSplitPane.HORIZONTAL_SPLIT;
    public static final int VERTICAL = JSplitPane.VERTICAL_SPLIT;
    private int orientation;

    public SideBar() {
        this(HORIZONTAL);
    }

    public SideBar(int orientation) {
        super();
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException();
        }
        this.orientation = orientation;
        updateUI();
    }

    public void setUI(SideBarUI ui) {
        super.setUI(ui);
    }

    public SideBarUI getUI() {
        return (SideBarUI) ui;
    }

    @Override
    public void updateUI() {
        setUI(new BasicSideBarUI());
    }

    @Override
    public String getUIClassID() {
        return "SideBarUI";
    }

    public int getOrientation() {
        return orientation;
    }

    public void setView(Component c) {
        getUI().setView(c);
    }

    public Component getView() {
        return getUI().getView();
    }

    public void addSlot(String name, Component c) {
        getUI().addSlot(name, c);
    }

    public void removeSlot(String name) {
        getUI().removeSlot(name);
    }

    public Slot getSlot(String name) {
        return getUI().getSlot(name);
    }

    public Map<String, Slot> getSlots() {
        return getUI().getSlots();
    }

    public void setSizer(ComponentSizer sizer) {
        getUI().setSizer(sizer);
    }

    public ComponentSizer getSizer() {
        return getUI().getSizer();
    }

    public void minify() {
        getUI().minify();
    }
}
