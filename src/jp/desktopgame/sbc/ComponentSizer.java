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

/**
 * サイドバーの特定のスロットが有効になったとき、対応するコンポーネントの可視領域を計算するインターフェイスです.
 *
 * @author desktopgame
 */
public interface ComponentSizer {

    public int computeWidth(String name, Component c);

    public int computeHeight(String name, Component c);
}
