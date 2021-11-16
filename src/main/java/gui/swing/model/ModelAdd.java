package gui.swing.model;

import gui.swing.event.EventAdd;

public class ModelAdd {
    private Object obj;
    private EventAdd evt;

    public ModelAdd(Object obj, EventAdd evt) {
        this.obj = obj;
        this.evt = evt;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public EventAdd getEvt() {
        return evt;
    }

    public void setEvt(EventAdd evt) {
        this.evt = evt;
    }
    
}
