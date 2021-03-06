package at.azak;
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

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;

/**
 * Provider object for the Java Card Terminal emulating.
 * @author LICEL LLC 
 * 
 * You can configure this by following system properties: 
 * 
 * Card ATR: 
 * com.licel.jcardsim.smartcardio.ATR 
 * 
 * Pre-Installed Applets:
 * com.licel.jcardsim.smartcardio.applet.{index}.AID
 * com.licel.jcardsim.smartcardio.applet.{index}.Class 
 * where {index} is value of 0..10 
 * 
 * NOTE: Applets and it's dependencies  must be available for the ClassLoader
 * before calling CardTerminal.connect();
 */
@SuppressWarnings("serial")
public class MyProvider extends Provider {

    public MyProvider() {
        super("MyProvider", 1.0d, "Smart Card Example");
        put("TerminalFactory.JCSFactory", "at.azak.JCSFactory");
          
    }
}