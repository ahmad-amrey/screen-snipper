/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensnipper;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author ahmad
 */
public class SnipSelector extends javax.swing.JFrame {

    static SnipSelector thisFrame;
    static BufferedImage screenshotImage;
    static int startX;
    static int startY;

    static int dragX;
    static int dragY;

    /**
     * Creates new form SnapSelector
     */
    public SnipSelector(GraphicsDevice gd) {
        super(gd.getDefaultConfiguration());
        setUndecorated(true);
        try {
            Image i;
            i = ImageIO.read(getClass().getResource("/screensnipper/ico.png"));
            setIconImage(i);
        } catch (IOException ex) {
        }
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        imageViewer = new DraggingLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        imageViewer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageViewer.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                imageViewerMouseDragged(evt);
            }
        });
        imageViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageViewerMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imageViewerMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                imageViewerMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageViewer, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageViewer, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );

        pack();
    }

    private void imageViewerMouseClicked(java.awt.event.MouseEvent evt) {
//        startX = evt.getX();
//        startY = evt.getY();
    }

    private void imageViewerMouseReleased(java.awt.event.MouseEvent evt) {
        int endX = evt.getX();
        int endY = evt.getY();

        int x = Math.min(startX, endX);
        int y = Math.min(startY, endY);

        int width = Math.max(startX, endX) - x;
        int hight = Math.max(startY, endY) - y;

//        System.out.println("startX = " + startX);
//        System.out.println("startY = " + startY);
//
//        System.out.println("endX = " + endX);
//        System.out.println("endY = " + endY);
//
//        System.out.println("x = " + x);
//        System.out.println("y = " + y);
//
//        System.out.println("width = " + width);
//        System.out.println("hight = " + hight);
        BufferedImage snappedImage = screenshotImage.getSubimage(x, y, width, hight);
        SnipViewer.main(null, snappedImage);
        setVisible(false); //you can't see me!
        dispose(); //Destroy the JFrame object
    }

    private void imageViewerMousePressed(java.awt.event.MouseEvent evt) {                                         
        startX = evt.getX();
        startY = evt.getY();
    }

    private void imageViewerMouseDragged(java.awt.event.MouseEvent evt) {                                         
        dragX = evt.getX();
        dragY = evt.getY();

        int x = Math.min(startX, dragX);
        int y = Math.min(startY, dragY);

        int width = Math.max(startX, dragX) - x;
        int hight = Math.max(startY, dragY) - y;

//        Graphics ImageViewerGraphics = ImageViewer.getGraphics();
//        int of = 180;
//        ImageViewerGraphics.setColor(new Color(of, of, of, 10));
//        ImageViewerGraphics.fillRect(x, y, width, hight);
//        ImageViewerGraphics.setColor(Color.black);
//        ImageViewerGraphics.drawRect(x, y, width, hight);
        imageViewer.x = x;
        imageViewer.y = y;
        imageViewer.w = width;
        imageViewer.h = hight;
        imageViewer.repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SnipSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SnipSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SnipSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SnipSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
//                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
////                    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
////                    Rectangle screenRect = ge.getMaximumWindowBounds();
//
//                    GraphicsDevice[] screens = ge.getScreenDevices();
//
//                    Rectangle allScreenBounds = new Rectangle();
//                    for (GraphicsDevice screen : screens) {
//                        Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();
//
//                        allScreenBounds.width += screenBounds.width;
//                        allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
//                    }
//
//                    screenshotImage = new Robot().createScreenCapture(allScreenBounds);
//                    thisFrame = new SnipSelector(screens[]);
                    
                    GraphicsDevice gd = MouseInfo.getPointerInfo().getDevice();
                    Rectangle screenBounds = gd.getDefaultConfiguration().getBounds();
                    screenshotImage = new Robot().createScreenCapture(screenBounds);
                    thisFrame = new SnipSelector(gd);
                    
                    BufferedImage grayImage = new BufferedImage(screenshotImage.getWidth(), screenshotImage.getHeight(), BufferedImage.TYPE_INT_RGB);

                    for (int i = 0; i < grayImage.getWidth(); i++) {
                        for (int j = 0; j < grayImage.getHeight(); j++) {
                            int oldRGB = screenshotImage.getRGB(i, j);
                            int red = (oldRGB >> 16) & 0x000000FF;
                            int green = (oldRGB >> 8) & 0x000000FF;
                            int blue = (oldRGB) & 0x000000FF;

                            int of = 10;
                            red = Math.min(255, red + of);
                            green = Math.min(255, green + of);
                            blue = Math.min(255, blue + of);

                            Color newColor = new Color(red, green, blue);
                            grayImage.setRGB(i, j, newColor.getRGB());
                        }
                    }
                    thisFrame.imageViewer.setIcon(new ImageIcon(grayImage));
                    thisFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

                    thisFrame.setVisible(true);
                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private DraggingLabel imageViewer;
}
