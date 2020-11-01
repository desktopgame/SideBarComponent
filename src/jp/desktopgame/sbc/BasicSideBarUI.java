/*
 * SideBarComponent
 *
 * Copyright (c) 2020 desktopgame
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package jp.desktopgame.sbc;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author desktopgame
 */
public class BasicSideBarUI extends SideBarUI {

    private SideBar sideBar;
    private JSplitPane splitPane;
    private JPanel leftTopPanel;
    private JPanel rightBottomPanel;
    private JPanel toolPanel;
    private JPanel viewPanel;
    private Box buttonBox;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private Component viewComp;
    private Map<String, Slot> cMap;
    private ComponentSizer sizer;
    private JPanel empty;

    @Override
    public void installUI(JComponent arg0) {
        super.installUI(arg0); //To change body of generated methods, choose Tools | Templates.
        this.sideBar = (SideBar) arg0;
        this.leftTopPanel = new JPanel();
        this.rightBottomPanel = new JPanel();
        this.cardPanel = new JPanel(this.cardLayout = new CardLayout());
        this.splitPane = new JSplitPane(sideBar.getOrientation(), leftTopPanel, rightBottomPanel);
        this.cMap = new HashMap<>();
        this.sizer = new DefaultComponentSizer();
        sideBar.setLayout(new BorderLayout());
        sideBar.add(splitPane, BorderLayout.CENTER);
        if (sideBar.getOrientation() == SideBar.HORIZONTAL) {
            this.toolPanel = leftTopPanel;
            this.viewPanel = rightBottomPanel;
            this.buttonBox = Box.createVerticalBox();
        } else {
            this.toolPanel = rightBottomPanel;
            this.viewPanel = leftTopPanel;
            this.buttonBox = Box.createHorizontalBox();
        }
        toolPanel.setLayout(new BorderLayout());
        if (sideBar.getOrientation() == SideBar.HORIZONTAL) {
            toolPanel.add(buttonBox, BorderLayout.WEST);
            toolPanel.add(cardPanel, BorderLayout.CENTER);
        } else {
            toolPanel.add(buttonBox, BorderLayout.SOUTH);
            toolPanel.add(cardPanel, BorderLayout.CENTER);
        }
        this.empty = new JPanel();
        empty.setMinimumSize(new Dimension(0, 0));
        empty.setPreferredSize(new Dimension(5, 5));
        empty.setMaximumSize(new Dimension(5, 5));
        empty.setSize(new Dimension(5, 5));
        viewPanel.setLayout(new BorderLayout());
        cardPanel.add(empty, "");
        copySize(empty);
        minify();
    }

    @Override
    public void uninstallUI(JComponent arg0) {
        super.uninstallUI(arg0); //To change body of generated methods, choose Tools | Templates.
        sideBar.remove(splitPane);
        sideBar.revalidate();
        sideBar.repaint();
        this.sideBar = null;
    }

    @Override
    public void setView(Component c) {
        if (this.viewComp != null) {
            viewPanel.remove(viewComp);
        }
        this.viewComp = c;
        viewPanel.add(c, BorderLayout.CENTER);
        viewPanel.revalidate();
        viewPanel.repaint();
    }

    @Override
    public Component getView() {
        return viewComp;
    }

    @Override
    public void addSlot(String name, Component c) {
        if (name.equals("")) {
            throw new IllegalArgumentException();
        }
        if (cMap.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        JToggleButton btn = new JToggleButton();
        if (sideBar.getOrientation() == SideBar.HORIZONTAL) {
            TextIcon t1 = new TextIcon(btn, name, TextIcon.Layout.HORIZONTAL);
            RotatedIcon r1 = new RotatedIcon(t1, RotatedIcon.Rotate.UP);
            btn.setIcon(r1);
        } else {
            btn.setText(name);
        }

        btn.addActionListener((e) -> {
            if (btn.isSelected()) {
                showSlot(name);
            } else {
                hideSlot();
            }
        });
        buttonBox.add(btn, name);
        cardPanel.add(c, name);
        toolPanel.revalidate();
        cMap.put(name, new Slot(name, btn, c));
    }

    @Override
    public void removeSlot(String name) {
        buttonBox.remove(cMap.get(name).toggleButton);
        buttonBox.revalidate();
        cardPanel.remove(cMap.get(name).tool);
        cardPanel.revalidate();
        cMap.remove(name);
    }

    @Override
    public Slot getSlot(String name) {
        return cMap.get(name);
    }

    @Override
    public Map<String, Slot> getSlots() {
        return new HashMap<>(cMap);
    }

    private boolean ignore;

    private int buttonWidth(int defV) {
        return getSlots().values().stream().map((e) -> e.toggleButton.getWidth()).findFirst().orElse(defV);
    }

    private int buttonHeight(int defV) {
        return getSlots().values().stream().map((e) -> e.toggleButton.getHeight()).findFirst().orElse(defV);
    }

    @Override
    public void setSizer(ComponentSizer sizer) {
        this.sizer = sizer;
    }

    @Override
    public ComponentSizer getSizer() {
        return sizer;
    }

    @Override
    public void minify() {
        cardLayout.show(cardPanel, "");
        cardPanel.revalidate();
        SwingUtilities.invokeLater(() -> {
            if (sideBar.getOrientation() == SideBar.HORIZONTAL) {
                splitPane.setDividerLocation(buttonWidth(50) + splitPane.getDividerSize());
            } else {
                splitPane.setDividerLocation(splitPane.getHeight() - (buttonHeight(50) + splitPane.getDividerSize()));
            }
        });
    }

    @Override
    public void showSlot(String name) {
        Slot slot = getSlot(name);
        if (!slot.toggleButton.isSelected()) {
            slot.toggleButton.setSelected(true);
            return;
        }
        cardLayout.show(cardPanel, name);
        ignore = true;
        getSlots().values()
                .stream()
                .filter((x) -> x.toggleButton != slot.toggleButton)
                .filter((x) -> x.toggleButton.isSelected()).forEach((x) -> x.toggleButton.setSelected(false));
        ignore = false;
        if (sideBar.getOrientation() == SideBar.HORIZONTAL) {
            splitPane.setDividerLocation(buttonWidth(50) + computeComponentWidth(slot) + splitPane.getDividerSize());
        } else {
            splitPane.setDividerLocation(splitPane.getHeight() - (computeComponentHeight(slot) + buttonHeight(50) + splitPane.getDividerSize()));
        }
        copySize(slot.tool);
    }

    @Override
    public void hideSlot() {
        if (ignore) {
            return;
        }
        cardLayout.show(cardPanel, "");
        if (sideBar.getOrientation() == SideBar.HORIZONTAL) {
            splitPane.setDividerLocation(buttonWidth(50) + splitPane.getDividerSize());
        } else {
            splitPane.setDividerLocation(splitPane.getHeight() - (buttonHeight(50) + splitPane.getDividerSize()));
        }
        copySize(empty);
    }

    private void copySize(Component c) {
        cardPanel.setMinimumSize(new Dimension(c.getMinimumSize()));
        cardPanel.setPreferredSize(new Dimension(c.getPreferredSize()));
        cardPanel.setMaximumSize(new Dimension(c.getMaximumSize()));
        cardPanel.setSize(empty.getSize());
    }

    private int computeComponentWidth(Slot s) {
        return sizer.computeWidth(s.name, s.tool);
    }

    private int computeComponentHeight(Slot s) {
        return sizer.computeHeight(s.name, s.tool);
    }
}
