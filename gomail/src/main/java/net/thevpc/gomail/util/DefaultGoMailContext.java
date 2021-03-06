/*
 * To change this license header, choose License Headers in Project Properties.
 *
 * and open the template in the editor.
 */
package net.thevpc.gomail.util;

import java.util.Map;

import net.thevpc.gomail.GoMailContext;
import net.thevpc.gomail.GoMailDataSource;
import net.thevpc.gomail.GoMailDataSourceRow;
import net.thevpc.gomail.GoMailProperties;

/**
 *
 * @author taha.bensalah@gmail.com
 */
public class DefaultGoMailContext implements GoMailContext {

//    PropertyPlaceholderHelper h = new PropertyPlaceholderHelper("${", "}");
    private GoMailProperties properties;
    private Map<String, GoMailDataSource> datasources;
    private RowExprEvaluator e;
    private GoMailDataSourceRow row;

    public DefaultGoMailContext(Map<String, GoMailDataSource> datasources, GoMailProperties properties, GoMailDataSourceRow row) {
        this.row = row;
        this.properties = properties;
        this.datasources = datasources;
        e = new DefaultRowExprEvaluator(this, row);
    }

    @Override
    public GoMailProperties getProperties() {
        return properties;
    }

    @Override
    public Map<String, GoMailDataSource> getRegisteredDataSources() {
        return datasources;
    }

    @Override
    public String eval(String expression) {
        if (expression == null) {
            return "";
        }
        return GoMailUtils.replaceDollarPlaceHolders(expression, x->e.evalExpressionString(x));
    }

}
