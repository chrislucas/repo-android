package tipstoapp.br.com.xplorespotlightlib.entities;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/**
 * Created by r028367 on 12/09/2017.
 */

public class MenuItemEntity implements android.view.MenuItem {

    private CharSequence mTitle;
    private CharSequence mCondensedTitle;
    @StringRes private int mResTitle;

    /**
     * Return the identifier for this menu item.  The identifier can not
     * be changed after the menu is created.
     *
     * @return The menu item's identifier.
     */
    @Override
    public int getItemId() {
        return 0;
    }

    /**
     * Return the group identifier that this menu item is part of. The group
     * identifier can not be changed after the menu is created.
     *
     * @return The menu item's group identifier.
     */
    @Override
    public int getGroupId() {
        return 0;
    }

    /**
     * Return the category and order within the category of this item. This
     * item will be shown before all items (within its category) that have
     * order greater than this value.
     * <p>
     * An order integer contains the item's category (the upper bits of the
     * integer; set by or/add the category with the order within the
     * category) and the ordering of the item within that category (the
     * lower bits). Example categories are {@link android.view.Menu#CATEGORY_SYSTEM},
     * {@link android.view.Menu#CATEGORY_SECONDARY}, {@link android.view.Menu#CATEGORY_ALTERNATIVE},
     * {@link android.view.Menu#CATEGORY_CONTAINER}. See {@link android.view.Menu} for a full list.
     *
     * @return The order of this item.
     */
    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * Change the mTitle associated with this item.
     *
     * @param title The new text to be displayed.
     * @return This Item so additional setters can be called.
     */
    @Override
    public MenuItem setTitle(CharSequence title) {
        this.mTitle = title;
        return this;
    }

    /**
     * Change the mTitle associated with this item.
     * <p>
     * Some menu types do not sufficient space to show the full mTitle, and
     * instead a condensed mTitle is preferred. See {@link android.view.Menu} for more
     * information.
     *
     * @param title The resource id of the new text to be displayed.
     * @return This Item so additional setters can be called.
     * @see #setTitleCondensed(CharSequence)
     */
    @Override
    public MenuItem setTitle(@StringRes int title) {
        this.mResTitle = title;
        return this;
    }

    /**
     * Retrieve the current mTitle of the item.
     *
     * @return The mTitle.
     */
    @Override
    public CharSequence getTitle() {
        return this.mTitle;
    }

    /**
     * Change the condensed mTitle associated with this item. The condensed
     * mTitle is used in situations where the normal mTitle may be too long to
     * be displayed.
     *
     * @param title The new text to be displayed as the condensed mTitle.
     * @return This Item so additional setters can be called.
     */
    @Override
    public MenuItem setTitleCondensed(CharSequence title) {
        this.mCondensedTitle = title;
        return this;
    }

    /**
     * Retrieve the current condensed mTitle of the item. If a condensed
     * mTitle was never set, it will return the normal mTitle.
     *
     * @return The condensed mTitle, if it exists.
     * Otherwise the normal mTitle.
     */
    @Override
    public CharSequence getTitleCondensed() {
        return this.mCondensedTitle;
    }

    /**
     * Change the icon associated with this item. This icon will not always be
     * shown, so the mTitle should be sufficient in describing this item. See
     * {@link android.view.Menu} for the menu types that support icons.
     *
     * @param icon The new icon (as a Drawable) to be displayed.
     * @return This Item so additional setters can be called.
     */
    @Override
    public MenuItem setIcon(Drawable icon) {
        return null;
    }

    /**
     * Change the icon associated with this item. This icon will not always be
     * shown, so the mTitle should be sufficient in describing this item. See
     * {@link android.view.Menu} for the menu types that support icons.
     * <p>'''
     * This method will set the resource ID of the icon which will be used to
     * lazily get the Drawable when this item is being shown.
     *
     * @param iconRes The new icon (as a resource ID) to be displayed.
     * @return This Item so additional setters can be called.
     */
    @Override
    public MenuItem setIcon(@DrawableRes int iconRes) {
        return null;
    }

    /**
     * Returns the icon for this item as a Drawable (getting it from resources if it hasn't been
     * loaded before).
     *
     * @return The icon as a Drawable.
     */
    @Override
    public Drawable getIcon() {
        return null;
    }

    /**
     * Change the Intent associated with this item.  By default there is no
     * Intent associated with a menu item.  If you set one, and nothing
     * else handles the item, then the default behavior will be to call
     * {@link Context#startActivity} with the given Intent.
     * <p>
     * <p>Note that setIntent() can not be used with the versions of
     * {@link android.view.Menu#add} that take a Runnable, because {@link Runnable#run}
     * does not return a value so there is no way to tell if it handled the
     * item.  In this case it is assumed that the Runnable always handles
     * the item, and the intent will never be started.
     *
     * @param intent The Intent to associated with the item.  This Intent
     *               object is <em>not</em> copied, so be careful not to
     *               modify it later.
     * @return This Item so additional setters can be called.
     * @see #getIntent
     */
    @Override
    public MenuItem setIntent(Intent intent) {
        return null;
    }

    /**
     * Return the Intent associated with this item.  This returns a
     * reference to the Intent which you can change as desired to modify
     * what the Item is holding.
     *
     * @return Returns the last value supplied to {@link #setIntent}, or
     * null.
     * @see #setIntent
     */
    @Override
    public Intent getIntent() {
        return null;
    }

    /**
     * Change both the numeric and alphabetic shortcut associated with this
     * item. Note that the shortcut will be triggered when the key that
     * generates the given character is pressed along with the ctrl key.
     * Also note that case is not significant and that alphabetic shortcut
     * characters will be displayed in lower case.
     * <p>
     * See {@link android.view.Menu} for the menu types that support shortcuts.
     *
     * @param numericChar The numeric shortcut key. This is the shortcut when
     *                    using a numeric (e.g., 12-key) keyboard.
     * @param alphaChar   The alphabetic shortcut key. This is the shortcut when
     *                    using a keyboard with alphabetic keys.
     * @return This Item so additional setters can be called.
     */
    @Override
    public MenuItem setShortcut(char numericChar, char alphaChar) {
        return null;
    }

    /**
     * Change the numeric shortcut associated with this item.
     * <p>
     * See {@link android.view.Menu} for the menu types that support shortcuts.
     *
     * @param numericChar The numeric shortcut key.  This is the shortcut when
     *                    using a 12-key (numeric) keyboard.
     * @return This Item so additional setters can be called.
     */
    @Override
    public MenuItem setNumericShortcut(char numericChar) {
        return null;
    }

    /**
     * Return the char for this menu item's numeric (12-key) shortcut.
     *
     * @return Numeric character to use as a shortcut.
     */
    @Override
    public char getNumericShortcut() {
        return 0;
    }

    /**
     * Change the alphabetic shortcut associated with this item. The shortcut
     * will be triggered when the key that generates the given character is
     * pressed along with the ctrl key. Case is not significant and shortcut
     * characters will be displayed in lower case. Note that menu items with
     * the characters '\b' or '\n' as shortcuts will get triggered by the
     * Delete key or Carriage Return key, respectively.
     * <p>
     * See {@link android.view.Menu} for the menu types that support shortcuts.
     *
     * @param alphaChar The alphabetic shortcut key. This is the shortcut when
     *                  using a keyboard with alphabetic keys.
     * @return This Item so additional setters can be called.
     */
    @Override
    public MenuItem setAlphabeticShortcut(char alphaChar) {
        return null;
    }

    /**
     * Return the char for this menu item's alphabetic shortcut.
     *
     * @return Alphabetic character to use as a shortcut.
     */
    @Override
    public char getAlphabeticShortcut() {
        return 0;
    }

    /**
     * Control whether this item can display a check mark. Setting this does
     * not actually display a check mark (see {@link #setChecked} for that);
     * rather, it ensures there is room in the item in which to display a
     * check mark.
     * <p>
     * See {@link android.view.Menu} for the menu types that support check marks.
     *
     * @param checkable Set to true to allow a check mark, false to
     *                  disallow. The default is false.
     * @return This Item so additional setters can be called.
     * @see #setChecked
     * @see #isCheckable
     * @see android.view.Menu#setGroupCheckable
     */
    @Override
    public MenuItem setCheckable(boolean checkable) {
        return null;
    }

    /**
     * Return whether the item can currently display a check mark.
     *
     * @return If a check mark can be displayed, returns true.
     * @see #setCheckable
     */
    @Override
    public boolean isCheckable() {
        return false;
    }

    /**
     * Control whether this item is shown with a check mark.  Note that you
     * must first have enabled checking with {@link #setCheckable} or else
     * the check mark will not appear.  If this item is a member of a group that contains
     * mutually-exclusive items (set via {@link android.view.Menu#setGroupCheckable(int, boolean, boolean)},
     * the other items in the group will be unchecked.
     * <p>
     * See {@link android.view.Menu} for the menu types that support check marks.
     *
     * @param checked Set to true to display a check mark, false to hide
     *                it.  The default value is false.
     * @return This Item so additional setters can be called.
     * @see #setCheckable
     * @see #isChecked
     * @see android.view.Menu#setGroupCheckable
     */
    @Override
    public MenuItem setChecked(boolean checked) {
        return null;
    }

    /**
     * Return whether the item is currently displaying a check mark.
     *
     * @return If a check mark is displayed, returns true.
     * @see #setChecked
     */
    @Override
    public boolean isChecked() {
        return false;
    }

    /**
     * Sets the visibility of the menu item. Even if a menu item is not visible,
     * it may still be invoked via its shortcut (to completely disable an item,
     * set it to invisible and {@link #setEnabled(boolean) disabled}).
     *
     * @param visible If true then the item will be visible; if false it is
     *                hidden.
     * @return This Item so additional setters can be called.
     */
    @Override
    public MenuItem setVisible(boolean visible) {
        return null;
    }

    /**
     * Return the visibility of the menu item.
     *
     * @return If true the item is visible; else it is hidden.
     */
    @Override
    public boolean isVisible() {
        return false;
    }

    /**
     * Sets whether the menu item is enabled. Disabling a menu item will not
     * allow it to be invoked via its shortcut. The menu item will still be
     * visible.
     *
     * @param enabled If true then the item will be invokable; if false it is
     *                won't be invokable.
     * @return This Item so additional setters can be called.
     */
    @Override
    public MenuItem setEnabled(boolean enabled) {
        return null;
    }

    /**
     * Return the enabled state of the menu item.
     *
     * @return If true the item is enabled and hence invokable; else it is not.
     */
    @Override
    public boolean isEnabled() {
        return false;
    }

    /**
     * Check whether this item has an associated sub-menu.  I.e. it is a
     * sub-menu of another menu.
     *
     * @return If true this item has a menu; else it is a
     * normal item.
     */
    @Override
    public boolean hasSubMenu() {
        return false;
    }

    /**
     * Get the sub-menu to be invoked when this item is selected, if it has
     * one. See {@link #hasSubMenu()}.
     *
     * @return The associated menu if there is one, else null
     */
    @Override
    public SubMenu getSubMenu() {
        return null;
    }

    /**
     * Set a custom listener for invocation of this menu item. In most
     * situations, it is more efficient and easier to use
     * {@link android.app.Activity#onOptionsItemSelected(MenuItem)} or
     * {@link android.app.Activity#onContextItemSelected(MenuItem)}.
     *
     * @param menuItemClickListener The object to receive invokations.
     * @return This Item so additional setters can be called.
     * @see android.app.Activity#onOptionsItemSelected(MenuItem)
     * @see android.app.Activity#onContextItemSelected(MenuItem)
     */
    @Override
    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener) {
        return null;
    }

    /**
     * Gets the extra information linked to this menu item.  This extra
     * information is set by the View that added this menu item to the
     * menu.
     *
     * @return The extra information linked to the View that added this
     * menu item to the menu. This can be null.
     * @see android.view.View.OnCreateContextMenuListener
     */
    @Override
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    /**
     * Sets how this item should display in the presence of an Action Bar.
     * The parameter actionEnum is a flag set. One of {@link #SHOW_AS_ACTION_ALWAYS},
     * {@link #SHOW_AS_ACTION_IF_ROOM}, or {@link #SHOW_AS_ACTION_NEVER} should
     * be used, and you may optionally OR the value with {@link #SHOW_AS_ACTION_WITH_TEXT}.
     * SHOW_AS_ACTION_WITH_TEXT requests that when the item is shown as an action,
     * it should be shown with a text label.
     *
     * @param actionEnum How the item should display. One of
     *                   {@link #SHOW_AS_ACTION_ALWAYS}, {@link #SHOW_AS_ACTION_IF_ROOM}, or
     *                   {@link #SHOW_AS_ACTION_NEVER}. SHOW_AS_ACTION_NEVER is the default.
     * @see ActionBar
     * @see #setActionView(View)
     */
    @Override
    public void setShowAsAction(int actionEnum) {

    }

    /**
     * Sets how this item should display in the presence of an Action Bar.
     * The parameter actionEnum is a flag set. One of {@link #SHOW_AS_ACTION_ALWAYS},
     * {@link #SHOW_AS_ACTION_IF_ROOM}, or {@link #SHOW_AS_ACTION_NEVER} should
     * be used, and you may optionally OR the value with {@link #SHOW_AS_ACTION_WITH_TEXT}.
     * SHOW_AS_ACTION_WITH_TEXT requests that when the item is shown as an action,
     * it should be shown with a text label.
     * <p>
     * <p>Note: This method differs from {@link #setShowAsAction(int)} only in that it
     * returns the current MenuItem instance for call chaining.
     *
     * @param actionEnum How the item should display. One of
     *                   {@link #SHOW_AS_ACTION_ALWAYS}, {@link #SHOW_AS_ACTION_IF_ROOM}, or
     *                   {@link #SHOW_AS_ACTION_NEVER}. SHOW_AS_ACTION_NEVER is the default.
     * @return This MenuItem instance for call chaining.
     * @see ActionBar
     * @see #setActionView(View)
     */
    @Override
    public MenuItem setShowAsActionFlags(int actionEnum) {
        return null;
    }

    /**
     * Set an action view for this menu item. An action view will be displayed in place
     * of an automatically generated menu item element in the UI when this item is shown
     * as an action within a parent.
     * <p>
     * <strong>Note:</strong> Setting an action view overrides the action provider
     * set via {@link #setActionProvider(ActionProvider)}.
     * </p>
     *
     * @param view View to use for presenting this item to the user.
     * @return This Item so additional setters can be called.
     * @see #setShowAsAction(int)
     */
    @Override
    public MenuItem setActionView(View view) {
        return null;
    }

    /**
     * Set an action view for this menu item. An action view will be displayed in place
     * of an automatically generated menu item element in the UI when this item is shown
     * as an action within a parent.
     * <p>
     * <strong>Note:</strong> Setting an action view overrides the action provider
     * set via {@link #setActionProvider(ActionProvider)}.
     * </p>
     *
     * @param resId Layout resource to use for presenting this item to the user.
     * @return This Item so additional setters can be called.
     * @see #setShowAsAction(int)
     */
    @Override
    public MenuItem setActionView(@LayoutRes int resId) {
        return null;
    }

    /**
     * Returns the currently set action view for this menu item.
     *
     * @return This item's action view
     * @see #setActionView(View)
     * @see #setShowAsAction(int)
     */
    @Override
    public View getActionView() {
        return null;
    }

    /**
     * Sets the {@link ActionProvider} responsible for creating an action view if
     * the item is placed on the action bar. The provider also provides a default
     * action invoked if the item is placed in the overflow menu.
     * <p>
     * <strong>Note:</strong> Setting an action provider overrides the action view
     * set via {@link #setActionView(int)} or {@link #setActionView(View)}.
     * </p>
     *
     * @param actionProvider The action provider.
     * @return This Item so additional setters can be called.
     * @see ActionProvider
     */
    @Override
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        return null;
    }

    /**
     * Gets the {@link ActionProvider}.
     *
     * @return The action provider.
     * @see ActionProvider
     * @see #setActionProvider(ActionProvider)
     */
    @Override
    public ActionProvider getActionProvider() {
        return null;
    }

    /**
     * Expand the action view associated with this menu item.
     * The menu item must have an action view set, as well as
     * the showAsAction flag {@link #SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW}.
     * If a listener has been set using {@link #setOnActionExpandListener(OnActionExpandListener)}
     * it will have its {@link OnActionExpandListener#onMenuItemActionExpand(MenuItem)}
     * method invoked. The listener may return false from this method to prevent expanding
     * the action view.
     *
     * @return true if the action view was expanded, false otherwise.
     */
    @Override
    public boolean expandActionView() {
        return false;
    }

    /**
     * Collapse the action view associated with this menu item.
     * The menu item must have an action view set, as well as the showAsAction flag
     * {@link #SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW}. If a listener has been set using
     * {@link #setOnActionExpandListener(OnActionExpandListener)} it will have its
     * {@link OnActionExpandListener#onMenuItemActionCollapse(MenuItem)} method invoked.
     * The listener may return false from this method to prevent collapsing the action view.
     *
     * @return true if the action view was collapsed, false otherwise.
     */
    @Override
    public boolean collapseActionView() {
        return false;
    }

    /**
     * Returns true if this menu item's action view has been expanded.
     *
     * @return true if the item's action view is expanded, false otherwise.
     * @see #expandActionView()
     * @see #collapseActionView()
     * @see #SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
     * @see OnActionExpandListener
     */
    @Override
    public boolean isActionViewExpanded() {
        return false;
    }

    /**
     * Set an {@link OnActionExpandListener} on this menu item to be notified when
     * the associated action view is expanded or collapsed. The menu item must
     * be configured to expand or collapse its action view using the flag
     * {@link #SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW}.
     *
     * @param listener Listener that will respond to expand/collapse events
     * @return This menu item instance for call chaining
     */
    @Override
    public MenuItem setOnActionExpandListener(OnActionExpandListener listener) {
        return null;
    }
}
