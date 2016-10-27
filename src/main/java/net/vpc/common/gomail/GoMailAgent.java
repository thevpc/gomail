/*
 * To change this license header, choose License Headers in Project Properties.
 *
 * and open the template in the editor.
 */
package net.vpc.common.gomail;

import java.io.IOException;
import java.util.Properties;

/**
 * sends a NON EXPANDABLE mail
 *
 * @author taha.bensalah@gmail.com
 */
public interface GoMailAgent {

    public int sendMessage(GoMailMessage mail, Properties properties, GoMailContext mailContext) throws IOException;
}
