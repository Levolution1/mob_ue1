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

import java.text.MessageFormat;
import javax.smartcardio.*;


/**
 * Card implementation class.
 * @author LICEL LLC
 */
public class JCSCard extends Card {
    // default protocol
    static final String T0_PROTOCOL = "T=0";
    // default ATR - NXP JCOP 31/36K
    static final String DEFAULT_ATR = "3BFA1800008131FE454A434F5033315632333298";
    // ATR system property name
    static final String ATR_SYSTEM_PROPERTY = "com.licel.jcardsim.smartcardio.ATR";
    // Applet AID system property template
    static final MessageFormat AID_SP_TEMPLATE = new MessageFormat("com.licel.jcardsim.smartcardio.applet.{0}.AID");
    // Applet ClassName system property template
    static final MessageFormat APPLET_CLASS_SP_TEMPLATE = new MessageFormat("com.licel.jcardsim.smartcardio.applet.{0}.Class");
    // ATR
    private ATR atr;
    // Simulator
//    private Simulator simulator;
    //
    private JCSCardChannel basicChannel;

    public JCSCard() {
//        simulator = new Simulator();
//        atr = new ATR(Hex.decode(System.getProperty(ATR_SYSTEM_PROPERTY, DEFAULT_ATR)));
        basicChannel = new JCSCardChannel(this, 0);
    }

    /**
     * Returns ATR configured by system property com.licel.jcardsim.smartcardio.ATR
     * Default ATR - 3BFA1800008131FE454A434F5033315632333298.
     */
    public ATR getATR() {
        return atr;
    }

    /**
     * Always returns T=0.
     */
    public String getProtocol() {
        return T0_PROTOCOL;
    }

    public CardChannel getBasicChannel() {
        return basicChannel;
    }

    /**
     * Always returns basic channel with id = 0
     * @throws CardException
     */
    public CardChannel openLogicalChannel() throws CardException {
        return basicChannel;
    }

    /**
     * Do nothing.
     */
    public void beginExclusive() throws CardException {
    }

    /**
     * Do nothing.
     */
    public void endExclusive() throws CardException {
    }

    public byte[] transmitControlCommand(int i, byte[] bytes) throws CardException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Do nothing.
     */
    public void disconnect(boolean bln) throws CardException {
    }

    ResponseAPDU transmitCommand(CommandAPDU capdu) {
		return null;
    }

    /**
     * powerdown/powerup
     */
    void reset(){
//        simulator.reset();
        // init applets
        initApplets();
    }
    
    /**
     * Init applets
     */
    private void initApplets() {
        for (int i = 0; i < 10; i++) {
            String appletAID = System.getProperty(AID_SP_TEMPLATE.format(new Object[]{new Integer(i)}));
            if (appletAID != null) {
                String appletClassName = System.getProperty(APPLET_CLASS_SP_TEMPLATE.format(new Object[]{new Integer(i)}));
                if (appletClassName != null) {
                    loadApplet(appletAID, appletClassName);
                }
            }
        }
    }

    /**
     * Install applet
     */
    private void loadApplet(String appletAID, String appletClassName) {
//        byte[] aidBytes = Hex.decode(appletAID);
//        if (aidBytes == null || aidBytes.length < 5 || aidBytes.length > 16) {
//            throw new IllegalArgumentException("AID must be in hex format 5..16 bytes length");
//        }
        try {
            Class appletClass = Class.forName(appletClassName);
//            simulator.loadApplet(new AID(aidBytes, (short) 0, (byte) aidBytes.length), appletClass);
        } catch (ClassNotFoundException ex) {
            throw new IllegalArgumentException("Applet class: "+appletClassName+" not found!", ex);
        }
    }
}
