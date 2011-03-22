package filetransfer;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.io.IOException;

public class AckSender {

    private DatagramSocket socket;
    private DatagramPacket packet;
    private ByteBuffer buffer;

    private final static int ACK_SIZE = 8;

    public AckSender(InetAddress destination, int ackPort)  throws SocketException {
	this.socket	 = new DatagramSocket();
	this.buffer	 = ByteBuffer.allocate(ACK_SIZE);
	this.packet	 = new DatagramPacket(buffer.array(), ACK_SIZE, destination, ackPort);
    }

    public boolean sendAck(long ackNumber) {
	buffer.putLong(0, ackNumber);

	try {
	    socket.send(packet);
	} catch (IOException e) {
	    return false;
	}

	return true;
    }

}