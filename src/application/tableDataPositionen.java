package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class tableDataPositionen
{ // data-object with id, name, value, color

	private final IntegerProperty id = new SimpleIntegerProperty();

	private final StringProperty name = new SimpleStringProperty();

	private final StringProperty value = new SimpleStringProperty();
	
	private final StringProperty cat = new SimpleStringProperty();
	
	private final StringProperty color = new SimpleStringProperty();

	public tableDataPositionen(final int id, final String name, final String value, final String cat, final String color)
	{
		this.id.set(id);
		this.name.set(name);
		this.value.set(value);
		this.cat.set(cat);
		this.color.set(color);
	}

	public IntegerProperty idProperty()
	{
		return id;
	}

	public StringProperty nameProperty()
	{
		return name;
	}

	public StringProperty valueProperty()
	{
		return value;
	}
	
	public StringProperty catProperty() {
		return cat;
	}
	
	public StringProperty colorProperty()
	{
		return color;
	}

	public int getID()
	{
		return idProperty().get();
	}

	public String getName()
	{
		return nameProperty().get();
	}

	public String getValue()
	{
		return valueProperty().get();
	}
	
	public String getCat() {
		return catProperty().get();
	}
	
	public String getColor()
	{
		return colorProperty().get();
	}

	public final void setID(int id)
	{
		idProperty().set(id);
	}

	public final void setName(String name)
	{
		nameProperty().set(name);
	}

	public final void setValue(String value)
	{
		valueProperty().set(value);
	}
	
	public final void setCat(String cat) {
		catProperty().set(cat);
	}
	
	public final void setColor(String color)
	{
		colorProperty().set(color);
	}
}
