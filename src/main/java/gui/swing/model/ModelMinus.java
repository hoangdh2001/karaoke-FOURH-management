package gui.swing.model;

import gui.swing.event.EventMinus;

public class ModelMinus {
    private Object object;
    private EventMinus event;

    public ModelMinus(Object object, EventMinus event) {
        this.object = object;
        this.event = event;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public EventMinus getEvent() {
        return event;
    }

    public void setEvent(EventMinus event) {
        this.event = event;
    }
    
}
