package com.alphasystem.arabickeyboard.ui;

public class ObservableObjectContext {

	private final String command;

	private final int keyCode;

	private final boolean selected;

	private final boolean shiftDown;

	/**
	 * @param command
	 * @param keyCode
	 * @param selected
	 * @param shiftDown
	 */
	public ObservableObjectContext(String command, int keyCode,
			boolean selected, boolean shiftDown) {
		this.command = command;
		this.keyCode = keyCode;
		this.selected = selected;
		this.shiftDown = shiftDown;
	}

	public String getCommand() {
		return command;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public boolean isSelected() {
		return selected;
	}

	public boolean isShiftDown() {
		return shiftDown;
	}
}
