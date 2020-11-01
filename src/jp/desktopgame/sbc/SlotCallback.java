/*
 * SideBarComponent
 *
 * Copyright (c) 2020 desktopgame
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package jp.desktopgame.sbc;

/**
 * スロットに格納されているコンポーネントがコールバックを受け取るために実装するインターフェイスです.
 *
 * @author desktopgame
 */
public interface SlotCallback {

    public void onShow();

    public void onHide();
}
