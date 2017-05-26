package hello;

import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;

@SpringUI
public class VaadinUI extends UI {

	private final CustomerRepository repo;

	private final CustomerEditor editor;

	//private final Game game;

	final Grid<Customer> grid;

	final TextField filter;



	private final Button addNewBtn;



	@Autowired
	public VaadinUI(CustomerRepository repo, CustomerEditor editor) {
		this.repo = repo;
		this.editor = editor;
		//this.game = game;
		this.editor.setHeight(450, Unit.PIXELS);
		//this.game.setHeight(500, Unit.PIXELS);
		this.grid = new Grid<>(Customer.class);
		this.filter = new TextField();
		//this.inputNumber = new TextField();

		this.addNewBtn = new Button("New player", FontAwesome.PLUS);
		//this.gameButton = new Button("Put number", FontAwesome.FORWARD); //*********
	}


	@Override
	protected void init(VaadinRequest request) {
		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);

//		VerticalLayout gameLayout = new VerticalLayout();
//		TextField  number = new TextField();
//		Button putNumber =    new Button("Put");
//		gameLayout.addComponents(number,putNumber);
//		putNumber.addClickListener( click -> gameLayout.addComponent(new Label(number.getValue())));

		//HorizontalLayout game = new HorizontalLayout(inputNumber, gameButton);
		//game.setWidth(600, Unit.PIXELS);

		//HorizontalLayout horizEditor = new HorizontalLayout(editor);//*******


		//VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor,game);//****
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);//****

		setContent(mainLayout);

		grid.setHeight(300, Unit.PIXELS);
		grid.setWidth(800, Unit.PIXELS);
		//grid.setColumns("id", "firstName", "lastName", "finalScore");
		grid.setColumns("id","firstName", "lastName", "winGames", "allGames","commonScore");

		filter.setPlaceholder("Filter by last name");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.LAZY);
		filter.addValueChangeListener(e -> listCustomers(e.getValue()));

		// Connect selected Customer to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> {
			editor.editCustomer(e.getValue());
		});

		editor.setWidth(680, Unit.PIXELS);//**
		//game.setWidth(400, Unit.PIXELS);//**


		// Instantiate and edit new Customer the new button is clicked
		//addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "", "")));
		addNewBtn.addClickListener(e -> editor.editCustomer(new Customer(
				"",
				"",
				"0",
				"0",
				"100")));

		// Listen changes made by the editor, refresh data from backend
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listCustomers(filter.getValue());
		});

		// Initialize listing
		listCustomers(null);
	}

	// tag::listCustomers[]
	void listCustomers(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repo.findAll());
		}
		else {
			grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
		}
	}
	// end::listCustomers[]

}
