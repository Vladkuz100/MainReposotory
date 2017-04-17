package me.java;

/**
 * Created by Vladkuz on 17.04.2017.
 */
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.*;
import com.vaadin.ui.themes.ValoTheme;
import javafx.scene.input.KeyCode;

import javax.xml.bind.Binder;

public class CustomerForm extends FormLayout{
    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private TextField email = new TextField("Email");
    private NativeSelect<CustomerStatus> status = new NativeSelect<>("Status");
    private DateField birthdate = new DateField("Birthday");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private CustomerService service =  CustomerService.getInstance();
    private Customer customer;
    private MyUI myUI;
    private com.vaadin.data.Binder<Customer> binder = new com.vaadin.data.Binder<>(Customer.class);

    public CustomerForm(MyUI myUI){
        this.myUI = myUI;

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        addComponents(firstName, lastName,email,status,birthdate,buttons);

        status.setItems(CustomerStatus.values());
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        binder.bindInstanceFields(this);
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());

    }

    public void setCustomer(Customer customer){
        this.customer = customer;
        binder.setBean(customer);

        delete.setVisible(customer.isPersisted());
        setVisible(true);
        firstName.selectAll();
    }


    private void delete (){
        service.delete(customer);
        myUI.updateList();
        setVisible(false);
    }

    private void save (){
        service.save(customer);
        myUI.updateList();
        setVisible(false);
    }
}
