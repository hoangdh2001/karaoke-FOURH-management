package gui.swing.model;

import gui.swing.event.EventAdd;

public class ModelAdd {
    private Object obj;
    private EventAdd event;

    public ModelAdd(Object obj, EventAdd event) {
        this.obj = obj;
        this.event = event;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public EventAdd getEvent() {
        return event;
    }

    public void setEvent(EventAdd event) {
        this.event = event;
    }
    
}
