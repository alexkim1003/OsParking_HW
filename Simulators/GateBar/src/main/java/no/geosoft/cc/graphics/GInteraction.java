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



/**
 * Common interface for all graphics interactions.
 * <p>
 * Typical usage:
 *
 * <pre>
 *    public class DrawInteraction implements GInteraction
 *    {
 *       private GObject   interaction_;
 *       private GSegment  line_;
 *
 *       public void event (GScene scene, int event, int x, int y)
 *       {
 *          switch (event) {
 *             case GWindow.BUTTON1_DOWN :
 *               // Create interaction object and segment and add to scene
 *               break;
 * 
 *             case GWindow.BUTTON1.DRAG :
 *                // extend segment with new x,y
 *                // and refresh graphics
 * 
 *             case ...
 *          }
 *       }
 *    }
 * </pre>
 *
 * The interaction is started by installing it in the GWindow:
 *
 * <pre>
 *    GInteraction drawInteraction = new DrawInteraction();
 *    window.startInteraction (drawInteraction);
 * </pre>
 *
 * The interaction is stopped if another interaction is started or if it
 * is explicitly stopped by <tt>GWindow.stopInteraction()</tt>. Before
 * the interaction is stopped a <tt>GWindow.ABORT</tt> event is passed
 * to the interaction.
 *
 * @see GWindow#startInteraction(GInteraction)
 * @see GWindow#stopInteraction()
 * @see ZoomInteraction
 * 
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */   
public interface GInteraction
{
  /**
   * Trigged by mouse events within the canvas.
   * @see no.geosoft.cc.graphics.GWindow
   *
   * @param scene  The scene of this event.
   * @param event  Event type 
   * @param x      X position of cursor.
   * @param y      Y position of cursor.   
   */
  public void event (GScene scene, int event, int x, int y);
}
