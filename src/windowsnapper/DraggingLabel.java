/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windowsnapper;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author ahmad
 */
public class DraggingLabel extends javax.swing.JLabel{
public int x, y, w, h;
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.black);
        g.drawRect(x, y, w, h);
    }
    
}
