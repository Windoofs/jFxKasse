package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class tableData
{ // Datenobjekt mit der ID, Datum und Konto

	private final IntegerProperty id = new SimpleIntegerProperty();

	private final StringProperty datum = new SimpleStringProperty();

	private final StringProperty konto = new SimpleStringProperty();

	public tableData(final int id, final String datum, final String konto)
	{
		this.id.set(id);
		this.datum.set(datum);
		this.konto.set(konto);
	}

	public IntegerProperty idProperty()
	{
		return id;
	}

	public StringProperty datumProperty()
	{
		return datum;
	}

	public StringProperty kontoProperty()
	{
		return konto;
	}

	public int getID()
	{
		return idProperty().get();
	}

	public String getDatum()
	{
		return datumProperty().get();
	}

	public String getKonto()
	{
		return kontoProperty().get();
	}

	public final void setID(int id)
	{
		idProperty().set(id);
	}

	public final void setdatum(String datum)
	{
		datumProperty().set(datum);
	}

	public final void setkonto(String konto)
	{
		kontoProperty().set(konto);
	}
}
