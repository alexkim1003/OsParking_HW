/* 
 * Copyright (C) 2015 Open Source Parking Inc.
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
package no.geosoft.cc.graphics;



import java.awt.Component;



/**
 * An AWT component with position hints for integration in the
 * graphics.
 * <p>
 * Typical usage:
 *
 * <pre>
 *    // Create an AWT component that should be integrated
 *    JButton button = new JButton ("Start");
 *    button.addActionListener (this);
 *
 *    // Create the GComponent wrapper w/positioning hint
 *    GComponent gbutton = new GComponent (button, GPosition.TOP);
 *
 *    // Create the GSegment the component is attached to
 *    GSegment segment = new GSegment();
 *    segment.setComponent (gbutton);
 * </pre>
 *
 * The component will be positioned in the canvas according to the
 * location of its owner GSegment and th position hint.
 * 
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */   
public class GComponent extends GPositional
{
  private static final int DEFAULT_POSITION_HINT = GPosition.CENTER |
                                                   GPosition.DYNAMIC;
  private final Component  component_;
  

  
  /**
   * Create an AWT component wrapper suitable for integration
   * in the graphics.
   *
   * @see #setPositionHint(int)
   * @see GPosition 
   * 
   * @param component     AWT component.
   * @param positionHint  Position hints.
   */
  public GComponent (Component component, int positionHint)
  {
    super (positionHint, false);

    component_ = component;
  }


  
  /**
   * Create an AWT component wrapper suitable for integration
   * in the graphics and with default position hints.
   * 
   * @param component  AWT component.
   */
  public GComponent (Component component)
  {
    this (component, DEFAULT_POSITION_HINT);
  }
  

  
  /**
   * Return the AWT component of this GComponent.
   * 
   * @return AWT component of this GComponent.
   */
  public Component getComponent()
  {
    return component_;
  }
  

  
  /**
   * COmpute the spatial size of this element.
   * 
   */
  void computeSize()
  {
    rectangle_.width  = component_.getWidth();
    rectangle_.height = component_.getHeight();
  }
}
