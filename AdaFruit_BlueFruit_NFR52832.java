package com.dhiyauddin.embedded;

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JSlider;

import com.fazecast.jSerialComm.SerialPort;

public class AdaFruit_BlueFruit_NFR52832 {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		JSlider slider = new JSlider();
		slider.setMaximum(100);
		window.add(slider);
		window.pack();
		window.setVisible(true);

		SerialPort ports[] = SerialPort.getCommPorts();
		System.out.println("Select a port : ");
		int i = 1;
		for (SerialPort port : ports) {
			System.out.println(i++ + "." + port.getSystemPortName());
		}

		Scanner j = new Scanner(System.in);
		int chosenPort = j.nextInt();

		SerialPort port = ports[chosenPort - 1];
		if (port.openPort()) {
			System.out.println("Successfully open port.");
		} else {
			System.out.println("Unable to open port.");
			return;
		}

		port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		Scanner data = new Scanner(port.getInputStream());
		while (data.hasNextLine()) {
			System.out.println(data.nextLine());

			int number = 0;
			try {
				number = Integer.parseInt(data.nextLine());
			} catch (Exception ex) {
			}
			slider.setValue(number);

		}

	}

}
