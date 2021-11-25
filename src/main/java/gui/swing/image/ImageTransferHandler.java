package gui.swing.image;

import gui.GD_DanhSachPhong;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.TransferHandler;

public class ImageTransferHandler extends TransferHandler {

    @Override
    public boolean canImport(TransferSupport support) {
        if(!support.isDrop()) {
            return false;
        }
        if(!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            return false;
        }
        boolean copySupported = (COPY & support.getSourceDropActions()) == COPY;
        if(copySupported) {
            support.setDropAction(COPY);
            return true;
        }
        return false;
    }

    @Override
    public boolean importData(TransferSupport support) {
        if(!support.isDrop()) {
            return false;
        }
        if(!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            return  false;
        }
        
        Transferable t = support.getTransferable();
        try {
            Object data = 
                    t.getTransferData(DataFlavor.javaFileListFlavor);
            final File file = ((List<File>) data).get(0);
            
            Thread loader = new Thread(new Runnable() {
                @Override
                public void run() {
//                    GD_DanhSachPhong.getDltp().showWaitGlassPane();
//                    try {
//                        BufferedImage image = GraphicsUtilities
//                                .loadCompatibleImage(file.toURI().toURL());
//                        Application.getMainFrame().setImage(image, file.getName());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        Application.getMainFrame().hideWaitGlassPane();
//                    }
                }
            });
        } catch (Exception e) {
        }
        return false;
    }
}
