package ch.bfh.bti7081.s2018.green.presenters;

import java.time.LocalDateTime;

import javax.persistence.PersistenceException;

import com.vaadin.server.Page;
import com.vaadin.ui.TextArea;

import ch.bfh.bti7081.s2018.green.DataContainer;
import ch.bfh.bti7081.s2018.green.NavigatorUI;
import ch.bfh.bti7081.s2018.green.models.entities.Event;
import ch.bfh.bti7081.s2018.green.models.managers.EventManager;
import ch.bfh.bti7081.s2018.green.views.ErrorView;
import ch.bfh.bti7081.s2018.green.views.EventListView;
import ch.bfh.bti7081.s2018.green.views.ScheduleAddView;

public class SchedulePresenter {

    private ScheduleAddView view;
    private DataContainer data;

    public SchedulePresenter(ScheduleAddView view) {
        this.view = view;
        this.data = DataContainer.getInstance();
      
        this.view.getBtnSave().addClickListener(clickEvent -> SaveSchedule());
    }
    
    private void SaveSchedule() {
    	
    	// Get data that was defined with ScheduleAddView
    	LocalDateTime dtfFrom = view.getDtfFrom().getValue();
    	LocalDateTime dtfTo = view.getDtfTo().getValue();
    	TextArea tfContent = view.getTfContent();
    	
    	// Prepare persistence
    	EventManager emSchedule = new EventManager();
    	    	
    	// Insertion
    	try {
    	if (dtfFrom.isBefore(dtfTo) && tfContent.isEmpty() == false ) {
    		emSchedule.add(new Event(dtfFrom,dtfTo,tfContent.getValue(),"",data.getCurrentPatient(),data.getCurrentStaff()));
			data.getCurrentNavigator().navigateTo(EventListView.NAME);
    	}
    	} catch(PersistenceException e) {
    		ErrorView.showError("Event couldn't be saved. Please try again!", Page.getCurrent());    		
    	}
    }
}