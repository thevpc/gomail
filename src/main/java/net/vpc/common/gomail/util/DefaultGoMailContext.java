/*
 * To change this license header, choose License Headers in Project Properties.
 *
 * and open the template in the editor.
 */
package net.vpc.common.gomail.util;

import java.util.Map;

import net.vpc.common.gomail.GoMailDataSourceRow;
import net.vpc.common.gomail.GoMailProperties;
import net.vpc.common.strings.StringConverter;
import net.vpc.common.gomail.GoMailContext;
import net.vpc.common.gomail.GoMailDataSource;
import net.vpc.common.strings.StringUtils;

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
        e = new RowExprEvaluator(this, row);
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

        return StringUtils.replaceDollarPlaceHolders(expression, new StringConverter() {

            @Override
            public String convert(String placeholderName) {
                return RowExprEvaluator.expressionToString(e.evalString(placeholderName),"");
            }
        });
    }

}
