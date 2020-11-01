/*
 * SideBarComponent
 *
 * Copyright (c) 2020 desktopgame
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package jp.desktopgame.sbct;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import jp.desktopgame.sbc.SideBar;

/**
 *
 * @author desktopgame
 */
public class Main {

    public static void main(String... args) {
        JFrame f = new JFrame();
        JPanel v = new JPanel(new BorderLayout());
        v.add(new JScrollPane(new JTextArea()), BorderLayout.CENTER);
        v.setPreferredSize(new Dimension(800, 600));
        SideBar sb = new SideBar(SideBar.HORIZONTAL);
        sb.setView(v);
        sb.addSlot("Tree", new JScrollPane(new JTree()));
        sb.addSlot("Text", new JScrollPane(new JTextArea()));
        sb.removeSlot("Text");
        sb.addSlot("Text", new JScrollPane(new JLabel("aaa")));
        SideBar sb2 = new SideBar(SideBar.VERTICAL);
        sb2.setView(sb);
        sb2.addSlot("Tree", new JScrollPane(new JTree()));
        sb2.addSlot("Text", new JScrollPane(new JTextArea()));

        f.setLayout(new BorderLayout());
        f.add(sb2, BorderLayout.CENTER);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SwingUtilities.invokeLater(() -> {
            sb.minify();
            sb2.minify();
            f.setVisible(true);
        });
    }
}
