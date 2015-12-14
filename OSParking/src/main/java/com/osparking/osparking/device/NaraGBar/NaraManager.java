/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osparking.osparking.device.NaraGBar;

import com.osparking.global.names.DeviceManager;
import com.osparking.osparking.ControlGUI;
import com.osparking.osparking.device.NaraGBar.NaraEnums.BarMessage;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.BarMessage.Broken;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.BarMessage.GateDownAction;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.BarMessage.GateDownOK;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.BarMessage.GateState_DOWN_ACTION;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.BarMessage.GateState_DOWN_OK;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.BarMessage.GateState_UP_ACTION;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.BarMessage.GateState_UP_OK;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.BarMessage.GateUpAction;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.BarMessage.GateUpLOCK;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.BarMessage.GateUpOK;
import com.osparking.osparking.device.NaraGBar.NaraEnums.BarStatus;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.BarStatus.UNKNOWN;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.down_action;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.down_ok;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.gate_down_action;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.gate_down_ok;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.gate_up_action;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.gate_up_ok;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.up_action;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.up_lock;
import static com.osparking.osparking.device.NaraGBar.NaraEnums.up_ok;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Open Source Parking Inc.
 */
class NaraManager extends Thread implements DeviceManager{
    ControlGUI mainGUI;
    private SerialPort serialPort = null;
    private Object msgArrived = new Object();
    private NaraEnums.BarMessage msg;
    public long openOrderTmMs = 0;
    private BarStatus barState = UNKNOWN;    
    
    NaraManager(ControlGUI mainGUI) {
        this.mainGUI = mainGUI;
    }
    
    @Override
    public void run() {
        int msTaken;
        
        while (true) {
            synchronized(msgArrived) {
                try {
                    // 포트에 메시지가 도달할 때까지 대기
                    msgArrived.wait();
                    
                    // 도달한 메시지를 처리
                    String suffixMsg = "";
                    if (msg == BarMessage.GateUpAction) {
                        if (openOrderTmMs == 0) {
                            suffixMsg = " (최초 자동 개방)" ;
                        } else {
                            msTaken = (int)(System.currentTimeMillis() - openOrderTmMs);
                            suffixMsg = " (ACK 시간: " + msTaken + "밀리초)" ;
                        }
                    } else if (msg == BarMessage.GateUpOK) {
                        if (openOrderTmMs == 0) {
                            suffixMsg = " (최초 자동 개방)" ;
                        } else {
                            msTaken = (int)(System.currentTimeMillis() - openOrderTmMs);
                            suffixMsg = " (소요 시간: " + msTaken + "밀리초)";
                        }
                        barState = BarStatus.OPENED;
                    } else if (msg == BarMessage.GateDownOK) {
                        barState = BarStatus.CLOSED;
                    } else if (msg == BarMessage.GateState_UP_OK) {
                        barState = BarStatus.OPENED;
                    } else if (msg == BarMessage.GateState_DOWN_OK) {
                        barState = BarStatus.CLOSED;
                    }
                                        
                    System.out.println("msg.getMessageUF() + suffixMsg");
                    if (msg == BarMessage.GateDownOK ||
                            msg == BarMessage.GateUpOK ) {
                        System.out.println("");
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(NaraManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
    }

    @Override
    public void finishConnection(Exception e, String description, byte deviceNo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stopOperation(String reason) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSocket(Socket eBoardSocket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Socket getSocket() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isNeverConnected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class RS_232_Manager implements SerialPortEventListener {

    ControlGUI mainGUI;
    
    RS_232_Manager(ControlGUI mainGUI) {
        this.mainGUI = mainGUI;
    }
    
    @Override
    public void serialEvent(SerialPortEvent e) {

        switch(e.getEventType()) {
            case SerialPortEvent.DATA_AVAILABLE:
                // 자료를 포트에서 읽어서 바른 차단기 메시지이면 차단기 명령 수신 대기자를 깨움
//                msg = readDeliveredMessage();
//                if (msg != Broken) {
//                    synchronized(msgArrived) {
//                        msgArrived.notify();
//                    }
//                }
                break;

            default:
                break;
        }
    }

    /**
     * 시리얼 포트의 입력 스트림에서 바이트들을 읽어서 이로부터 메시지를 뽑아낸다
     * @param inStream 시리얼 포트에 연결된 입력 스트림
     * @return 도달한 메시지 유형(타입), 메시지가 깨지거나 EOF 등 예외상황이 발생하면 Broken 값을 반환한다.
     */
    private BarMessage readDeliveredMessage() {
        BarMessage messageType = Broken;
        byte[] barMessage = new byte[100];
        int byteIndex = 0;
        byte posiETX;
        
        InputStream inStream = null;
//        try {
//            inStream = mainGUI.getSerialPort().getInputStream();
//        } catch (IOException ex) {
//            Logger.getLogger(RS_232_Manager.class.getName()).log(Level.SEVERE, null, ex);
//        }        
        while ( true )
        {
            try {
                posiETX = (byte)(inStream.read());
                if (posiETX == -1) {
                    break;
                } 
                
                barMessage[byteIndex++] = posiETX;
                
                if (posiETX == 3) {
                    // 도착한 메시지 유형을 식별한다
                    messageType = getMessageType(barMessage, byteIndex - 1);
                    break;
                }
            }
            catch (IOException ex) {
//                Logger.getLogger(RS_232_Manager.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
        try {
            inStream.close();
        } catch (IOException ex) {
            Logger.getLogger(RS_232_Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messageType;
    }

    /**
     * Identify message type out of byte array and its length.
     * @param barMessage message byte array 
     * @param length number of bytes in the message
     * @return Gate bar message type
     */
    private BarMessage getMessageType(byte[] barMessage, int length) {
        BarMessage type = Broken;
        
        if (barMessage[0] == 2) {
            byte[] byteCore = Arrays.copyOfRange(barMessage, 1, length);
            
            String msgCore = new String(byteCore);
            if (msgCore.substring(0, 5).equals("GATE=")) {
                String statusCore = null;
                int commaLoc = msgCore.indexOf((int)',');
                
                if (commaLoc > -1) {
                    statusCore = msgCore.substring(5, commaLoc);
                    switch(statusCore) {
                        case up_lock:
                            type = GateUpLOCK;
                            break;
                        case up_ok:
                            type = GateState_UP_OK;
                            break;
                        case up_action:
                            type = GateState_UP_ACTION;
                            break;
                        case down_ok:
                            type = GateState_DOWN_OK;
                            break;
                        case down_action:
                            type = GateState_DOWN_ACTION;
                            break;
                        default:
                            break;
                    }
                }
            } else {
                switch(new String(byteCore)) {
                    case gate_up_action:
                        type = GateUpAction;
                        break;
                    case gate_up_ok:
                        type = GateUpOK;
                        break;
                    case gate_down_action:
                        type = GateDownAction;
                        break;
                    case gate_down_ok:
                        type = GateDownOK;
                        break;
                    default:
                        break;
                }
            }
        }
        return type;
    }
}
