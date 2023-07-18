
package lib.common;

import java.awt.*;

import javax.swing.border.Border;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

public class RoundedBorder implements Border
{

    int radius;
    Color color;

    public RoundedBorder(int radius, Color color)
    {
        this.radius = radius;
        this.color = color;
    }

    public Insets getBorderInsets(Component c)
    {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    public boolean isBorderOpaque()
    {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape round = new RoundRectangle2D.Float(x, y, width - 1, height - 1, radius, radius);
        g2.setColor(color);
        g2.draw(round);
    }
}