/* JNativeHook: Global keyboard and mouse hooking for Java.
 * Copyright (C) 2006-2012 Alexander Barker.  All Rights Received.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jnativehook.mouse;

//Imports
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeInputEvent;
 
/**
 * An event which indicates that a mouse action occurred on the system.  
 * This event is not limited to a particular component's scope or visibility.
 * <p/>
 * 
 * This low-level event is generated natively for:
 * <ul>
 * 	<li>
 * 		Mouse Events
 * 		<ul>
 * 			<li>a mouse button is pressed</li>
 * 			<li>a mouse button is released</li>
 * 			<li>the mouse wheel was moved</li>
 * 		</ul>
 * 	</li>
 * 	<li>
 * 		Mouse Motion Events
 * 		<ul>
 * 			<li>the mouse is moved
 * 			<li>the mouse is dragged</li>
 * 		</ul>
 * 	</li>
 * </ul>
 * <p/>
 * 
 * A <code>NativeMouseEvent</code> object is passed to every 
 * <code>NativeMouseListener</code> object which is registered to receive mouse 
 * events using the {@link GlobalScreen#addNativeMouseListener} method. The  
 * <code>GlobalScreen</code> object then dispatches a 
 * <code>NativeMouseEvent</code> to each listener.
 * <p/>
 * 
 * A <code>NativeMouseEvent</code> object is also passed to every 
 * <code>NativeMouseMotionListener</code> object which has been registered to 
 * receive mouse motion events using the 
 * {@link GlobalScreen#addNativeMouseListener} method. The 
 * <code>GlobalScreen</code> object then dispatches a 
 * <code>NativeMouseEvent</code> to each listener. 
 * <p/>
 * 
 * When a mouse button is clicked, events are generated and sent to the 
 * registered <code>NativeMouseListeners</code>. The state of modifiers can be 
 * retrieved using {@link NativeInputEvent#getModifiers}. All modifiers returned 
 * by {@link NativeInputEvent#getModifiers} reflect the current state of all 
 * modifiers and buttons.
 * 
 * @author	Alexander Barker (<a href="mailto:alex@1stleg.com">alex@1stleg.com</a>)
 * @since	1.0
 * 
 * @see GlobalScreen
 * @see NativeMouseListener
 * @see NativeMouseMotionListener
 */
public class NativeMouseEvent extends NativeInputEvent {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1241061060064904571L;
	
	/**
	 * The native mouse event's x pointer position.
	 * 
	 * @see #getX()
	 */
	private int x;
	
	/**
	 * The native mouse event's y pointer position.
	 * 
	 * @see #getY()
	 */
	private int y;
	
	/**
	 * Indicates which, if any, of the mouse buttons has changed state.
	 *
	 * The only legal values are the following constants:
	 * <code>NOBUTTON</code>,
	 * <code>BUTTON1</code>,
	 * <code>BUTTON2</code>,
	 * <code>BUTTON3</code>,
	 * <code>BUTTON4</code> or
	 * <code>BUTTON5</code>
	 */
	private int button;

	/** The first number in the range of id's used for native mouse events. */
	public static final int NATIVE_MOUSE_FIRST		= 2500;
	
	/** The last number in the range of id's used for native mouse events. */
	public static final int NATIVE_MOUSE_LAST		= 2504;
	
	/**
	 * The native mouse pressed event. This <code>NativeMouseEvent</code>
	 * occurs when a mouse button is depressed.
	 */
	public static final int NATIVE_MOUSE_PRESSED	= NATIVE_MOUSE_FIRST;
	
	/**
	 * The native mouse released event. This <code>NativeMouseEvent</code>
	 * occurs when a mouse button is released.
	 */
	public static final int NATIVE_MOUSE_RELEASED	= 1 + NATIVE_MOUSE_FIRST;
	
	/**
	 * The native mouse moved event. This <code>NativeMouseEvent</code>
	 * occurs when the mouse pointer changes position.
	 */
	public static final int NATIVE_MOUSE_MOVED		= 2 + NATIVE_MOUSE_FIRST;
	
	/**
	 * The native mouse dragged event. This <code>NativeMouseEvent</code>
	 * is not currently implemented.
	 */
	public static final int NATIVE_MOUSE_DRAGGED	= 3 + NATIVE_MOUSE_FIRST;
	
	/**
	 * The native mouse dragged event. This <code>NativeMouseEvent</code>
	 * is not currently implemented.
	 */
	public static final int NATIVE_MOUSE_WHEEL		= 4 + NATIVE_MOUSE_FIRST; 

	
	/** Indicates no mouse buttons; used by getButton(). */
	public static final int	NOBUTTON	= 0;
	
	/** Indicates mouse button #1; used by getButton(). */
	public static final int	BUTTON1		= 1;
	
	/** Indicates mouse button #2; used by getButton(). */
	public static final int	BUTTON2		= 2;
	
	/** Indicates mouse button #3; used by getButton(). */
	public static final int	BUTTON3		= 3;
	
	/** Indicates mouse button #4; used by getButton(). */
	public static final int	BUTTON4		= 4;
	
	/** Indicates mouse button #5; used by getButton(). */
	public static final int	BUTTON5		= 5;
	
	
	/**
	 * Instantiates a new <code>NativeMouseEvent</code> object.
	 *
	 * @param id The type of event.
	 * @param when a long integer that gives the time the event occurred
	 * @param modifiers a modifier mask describing the modifier keys and mouse 
	 * buttons active for the event. 
	 * <code>NativeInputEvent</code> _MASK modifiers should be used as they are 
	 * not compatible with the extended _DOWN_MASK or the old _MASK 
	 * <code>InputEvent</code> modifiers.
	 * @param x The x coordinate of the native pointer.
	 * @param y The y coordinate of the native pointer.
	 * @param button The mouse buttons that has changed state. NOBUTTON, 
	 * BUTTON1, BUTTON2, BUTTON3, BUTTON5 or BUTTON5.
	 */
	public NativeMouseEvent(int id, long when, int modifiers, int x, int y, int button) {
		super(GlobalScreen.getInstance(), id, when, modifiers);
		
		this.x = x;
		this.y = y;
		this.button = button;
	}

	/**
	 * Instantiates a new <code>NativeMouseEvent</code> object.
	 *
	 * @param id an integer that identifies the event
	 * @param when a long integer that gives the time the event occurred
	 * @param modifiers a modifier mask describing the modifier keys and mouse 
	 * buttons active for the event. 
	 * <code>NativeInputEvent _MASK</code> modifiers should be used as they are 
	 * not compatible with the extended _DOWN_MASK or the old _MASK 
	 * <code>InputEvent</code> modifiers.
	 * @param x The x coordinate of the native pointer.
	 * @param y The y coordinate of the native pointer.
	 */
	public NativeMouseEvent(int id, long when, int modifiers, int x, int y) {
		this(id, when, modifiers, x, y, NOBUTTON);
	}

	/**
	 * Returns which, if any, of the mouse buttons has changed state.
	 *
	 * @return one of the following constants:
	 * <code>NOBUTTON</code>,
	 * <code>BUTTON1</code>,
	 * <code>BUTTON2</code>,
	 * <code>BUTTON3</code>,
	 * <code>BUTTON4</code> or
	 * <code>BUTTON5</code>
	 */
	public int getButton() {
		return button;
	}

	/**
	 * Returns the x coordinate of the native pointer.
	 *
	 * @return x an integer indicating horizontal position of the native pointer
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the y coordinate of the native pointer.
	 *
	 * @return y an integer indicating horizontal position of the native pointer
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns a parameter string identifying the native event.
	 * This method is useful for event-logging and debugging.
	 *
	 * @return A string identifying the native event and its attributes.
	 */
	public String  paramString() {
		StringBuilder param = new StringBuilder(255); 
		
		switch(getID()) {
			case NATIVE_MOUSE_PRESSED:
				 param.append("NATIVE_MOUSE_PRESSED");
			break;
			
			case NATIVE_MOUSE_RELEASED:
				param.append("NATIVE_MOUSE_RELEASED");
			break;
			
			case NATIVE_MOUSE_MOVED:
				param.append("NATIVE_MOUSE_MOVED");
			break;
			
			case NATIVE_MOUSE_DRAGGED:
				param.append("NATIVE_MOUSE_DRAGGED");
			break;
			
			case NATIVE_MOUSE_WHEEL:
				param.append("NATIVE_MOUSE_WHEEL");
			break;
			
			default:
				param.append("unknown type");
			break;
		}
		param.append(',');
		
		param.append('(');
		param.append(x);
		param.append(',');
		param.append(y);
		param.append(')');
		param.append(',');
		
		
		param.append("button=");
		param.append(button);
		
		if (getModifiers() != 0) {
			param.append(',');
			param.append("modifiers=");
			param.append(getModifiersText(getModifiers()));
		}
		
		return param.toString();
	}
}