package in.websnoox.tappTablet.dataReport.wheel;

/**
 * Wheel scrolled listener interface.
 */
public interface OnWheelScrollListener {
	/**
	 * Callback method to be invoked when scrolling started.
	 * 
	 * @param wheel
	 *            the spinnerwheel view whose state has changed.
	 */
	void onScrollingStarted(ABWheel wheel);

	/**
	 * Callback method to be invoked when scrolling ended.
	 * 
	 * @param wheel
	 *            the spinnerwheel view whose state has changed.
	 */
	void onScrollingFinished(ABWheel wheel);
}
