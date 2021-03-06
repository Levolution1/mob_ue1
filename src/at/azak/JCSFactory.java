/*
 * Copyright 2012 Licel LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.azak;

import javax.smartcardio.CardTerminals;
import javax.smartcardio.TerminalFactorySpi;

/**
 * Implementation of TerminalFactorySpi.
 * @author LICEL LLC
 */
public class JCSFactory extends TerminalFactorySpi {

    public JCSFactory() {
    }

    public JCSFactory(Object params) {
    }

    protected CardTerminals engineTerminals() {
    	//TODO
        return new JCSCardTerminals();
    }
}

