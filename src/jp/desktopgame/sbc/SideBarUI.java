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
import javax.swing.plaf.ComponentUI;

/**
 * SideBar用のプラグイン可能なLook & Feelインタフェースです.
 *
 * @author desktopgame
 */
public abstract class SideBarUI extends ComponentUI {

    /**
     * 常に表示されるコンポーネントを設定します.
     *
     * @param c
     */
    public abstract void setView(Component c);

    /**
     * 常に表示されるコンポーネントを返します.
     *
     * @return
     */
    public abstract Component getView();

    /**
     * スロットを追加します.
     *
     * @param name
     * @param c
     */
    public abstract void addSlot(String name, Component c);

    /**
     * スロットを削除します.
     *
     * @param name
     */
    public abstract void removeSlot(String name);

    /**
     * スロットを返します.
     *
     * @param name
     * @return
     */
    public abstract Slot getSlot(String name);

    /**
     * スロットを全て返します.
     *
     * @return
     */
    public abstract Map<String, Slot> getSlots();

    /**
     * サイザーを設定します.
     *
     * @param sizer
     */
    public abstract void setSizer(ComponentSizer sizer);

    /**
     * サイザーを返します.
     *
     * @return
     */
    public abstract ComponentSizer getSizer();

    /**
     * ディバイダーの表示領域を可能な限り小さくします.
     */
    public abstract void minify();

    /**
     * 指定のスロットを表示します.
     *
     * @param name
     */
    public abstract void showSlot(String name);

    /**
     * スロットを非表示にします.
     */
    public abstract void hideSlot();
}
