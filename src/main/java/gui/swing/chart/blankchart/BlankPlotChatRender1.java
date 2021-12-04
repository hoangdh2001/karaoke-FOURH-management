package gui.swing.chart.blankchart;

import java.awt.Graphics2D;

public abstract class BlankPlotChatRender1 {

    public abstract String getLabelText(int index);

    public abstract void renderSeries(BlankPlotChart1 chart, Graphics2D g2, SeriesSize size, int index);
}
