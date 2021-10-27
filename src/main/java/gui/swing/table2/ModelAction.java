package gui.swing.table2;

import entity.HoaDon;
import entity.PhieuDatPhong;
import entity.TrangThaiPhieuDat;

public class ModelAction {
    private EventAction event;
    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

    public ModelAction(Object o, EventAction event) {
        this.obj = o;
        this.event = event;
    }

    public ModelAction() {
    }
}
