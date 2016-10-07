package com.mne.client.ui;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.github.gwtbootstrap.client.ui.DataGrid;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.datepicker.client.ui.DateBox;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.RangeChangeEvent;
import com.google.inject.Inject;
import com.mne.client.resource.Messages;
import com.mne.client.ui.component.CurrencySuggestOracle;
import com.mne.client.ui.component.CurrencySuggestion;
import com.mne.common.model.Currency;
import com.mne.common.model.CurrencyRate;

/**
 * Main Panel
 * 
 * main panel of the currency monitor application
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public class MainPanel extends Composite implements com.mne.client.ui.presenter.CurrencyRatesPresenter.Display {
    interface MainPanelUiBinder extends UiBinder<Widget, MainPanel> {}

    private final static int CURRENCY_SUGGEST_LIMIT = 10;
    private static MainPanelUiBinder uiBinder = GWT.create(MainPanelUiBinder.class);
    
    @Inject
    Messages messages;
    
    
    private ListDataProvider<CurrencyRate> dataProviderCurrencyRates;
    
    public MainPanel() {
        super();		
        initWidget(uiBinder.createAndBindUi(this));
        dataProviderCurrencyRates = new ListDataProvider<CurrencyRate>();
        initCurrencyRatesGrid(currencyRatesGrid);
        currencies.setAutoSelectEnabled(true);
        currencies.setLimit(CURRENCY_SUGGEST_LIMIT);
        historicDate.setEndDate_(new Date());
        historicDate.setStartDate("1999-01-01");
        historicDate.setValue(new Date());
        historicDate.setFormat("yyyy-mm-dd");
        historicDate.reconfigure();
    }
    
    @UiField
    DataGrid<CurrencyRate> currencyRatesGrid = new DataGrid<CurrencyRate>(10);

    
    @UiField(provided=true)
    SuggestBox currencies = new SuggestBox(new CurrencySuggestOracle());
    
    @UiField
    DateBox historicDate;
    
    private void initCurrencyRatesGrid(DataGrid<CurrencyRate> grid) {
        grid.setEmptyTableWidget(new Label("Loading..."));
        dataProviderCurrencyRates.addDataDisplay(grid);
        
        final TextColumn<CurrencyRate> idColumn = new TextColumn<CurrencyRate>() {

            @Override
            public String getValue(CurrencyRate currencyRate) {
                return currencyRate.toString();
            }
            
        };
        
        idColumn.setSortable(true);
        
        final TextColumn<CurrencyRate> rateColumn = new TextColumn<CurrencyRate>() {

            @Override
            public String getValue(CurrencyRate currencyRate) {
                return currencyRate.getRate().toString();
            }
            
        };
        
        rateColumn.setSortable(true);
        grid.addColumn(idColumn, messages.currency());
        grid.addColumn(rateColumn, messages.currencyRate());
        
        final ListHandler<CurrencyRate> idColumnSortHandler = new ListHandler<CurrencyRate>(dataProviderCurrencyRates.getList());

        idColumnSortHandler.setComparator(idColumn, new Comparator<CurrencyRate>() {
            
            @Override
            public int compare(CurrencyRate o1, CurrencyRate o2) {
                return o1.getCurrency().getCode().compareTo(o2.getCurrency().getCode());
            }
        });
        grid.addColumnSortHandler(idColumnSortHandler);
        

        final ListHandler<CurrencyRate> rateColumnSortHandler = new ListHandler<CurrencyRate>(dataProviderCurrencyRates.getList());

        rateColumnSortHandler.setComparator(rateColumn, new Comparator<CurrencyRate>() {
            
            @Override
            public int compare(CurrencyRate o1, CurrencyRate o2) {
                return o1.getRate().compareTo(o2.getRate());
            }
        });
        
        grid.addColumnSortHandler(rateColumnSortHandler);
        
        grid.addRangeChangeHandler(new RangeChangeEvent.Handler() {
            
            @Override
            public void onRangeChange(RangeChangeEvent event) {
                // TODO Auto-generated method stub
                
            }
        });
        
    }

    @Override
    public void setCurrencies(List<Currency> currencies) {
        final CurrencySuggestOracle oracle = (CurrencySuggestOracle) this.currencies.getSuggestOracle();
        currencies.forEach(c -> oracle.addItem(new CurrencySuggestion(c)));
    }

    @Override
    public void setCurrencyRatesTable(List<CurrencyRate> currencyRates) {
        this.dataProviderCurrencyRates.setList(currencyRates);
        
    }

    @Override
    public SuggestBox getCurrencyComponent() {
        return this.currencies;
    }

    @Override
    public DateBox getHistoricDate() {
        return this.historicDate;
    }
}
