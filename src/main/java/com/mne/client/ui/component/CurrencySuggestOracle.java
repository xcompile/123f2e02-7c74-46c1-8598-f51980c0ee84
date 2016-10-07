package com.mne.client.ui.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gwt.user.client.ui.SuggestOracle;
import com.mne.common.model.Currency;

/**
 * currency suggestion oracle
 * 
 * custom implementation for {@link SuggestOracle} for {@link Currency}
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public class CurrencySuggestOracle extends SuggestOracle {

    private List<CurrencySuggestion> items;
    
    @Override
    public void requestSuggestions(Request request, Callback callback) {
        Response resp = new Response(matchingQuery(request.getQuery(),
                request.getLimit()));
        callback.onSuggestionsReady(request, resp);
    }

    /**
     * matching entries by text
     * 
     * @param query	query string
     * @param limit	result limit
     * 
     * @return results
     */
    private Collection<? extends Suggestion> matchingQuery(String query,
            int limit) {
        // yes, this could be smarter
        final String lowerCaseQuery = query.toLowerCase();
        final List<CurrencySuggestion> result = this.items.stream()
                .filter(i -> i.getDisplayString().toLowerCase().contains(lowerCaseQuery) )
                .limit(limit)
                .collect(Collectors.toList());
        return result;
    }
    
    public void addItem(CurrencySuggestion currency){
        if (null == this.items) {
            this.items = new ArrayList<CurrencySuggestion>();
        }
        this.items.add(currency);
    }
    
    public void removeItem(CurrencySuggestion currency){
        if (null == this.items) {
            this.items.remove(currency);
        }
    }

}
