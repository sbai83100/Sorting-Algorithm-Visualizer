package gui;

import java.util.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Visualizer extends JFrame {

	private int length = 256;
	private int factor = 8;
	private int[] A;
	private Color[] colors;

	private int i;
	private int j;
	private int k;

	private InsertionSort graphics;
	private JLabel swapsLabel;
	private int numSwaps;
	private JLabel compsLabel;
	private int numComps;

	public Visualizer() {

		setSize(1000,600);
		setTitle("Sorting Algorithm Visualizer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		createArrays();

		graphics = new InsertionSort();
		graphics.setSize(800, 600);
		add(graphics);

		swapsLabel = new JLabel();
		swapsLabel.setText("Total Swaps: " + numSwaps);
		swapsLabel.setFont(new Font("Comic Sans", Font.PLAIN, 25));
		swapsLabel.setBounds(650, 100, 250, 200);
		add(swapsLabel);

		compsLabel = new JLabel();
		compsLabel.setText("Total Comparisons: " + numComps);
		compsLabel.setFont(new Font("Comic Sans", Font.PLAIN, 25));
		compsLabel.setBounds(650, 200, 500, 200);
		add(compsLabel);

		shuffleArray();

		int delay = 2000;
		Timer timer = new Timer( delay, e -> {
			System.out.println("Hello, World!");
			graphics.sort();
		} );
		timer.setRepeats( false );//make sure the timer only runs once
		timer.start();

		setVisible(true);

	}

	private void createArrays() {
		/*
		colors = new Color[length];
		A = new int[length];
		for (int i = 0; i < length; i++) {
			colors[i] = new Color(i, 0, 0);
			A[i] = i;
		}
		 */

		colors = new Color[192];
		A = new int[192];

		int index = 0;
		for (int i = 1; i <= 256; i+=factor) {
			colors[index] = new Color(255, i-1, 0);
			A[index] = index;
			index++;
		}

		for (int i = 256; i >= 1; i-=factor) {
			colors[index] = new Color(i-1, 255, 0);
			A[index] = index;
			index++;
		}

		for (int i = 1; i <= 256; i+=factor) {
			colors[index] = new Color(0, 255, i-1);
			A[index] = index;
			index++;
		}

		for (int i = 256; i >= 1; i-=factor) {
			colors[index] = new Color(0, i-1, 255);
			A[index] = index;
			index++;
		}

		for (int i = 1; i <= 256; i+=factor) {
			colors[index] = new Color(i-1, 0, 255);
			A[index] = index;
			index++;
		}

		for (int i = 256; i >= 1; i-=factor) {
			colors[index] = new Color(255, 0, i-1);
			A[index] = index;
			index++;
		}
	}

	public void shuffleArray() {
		Random rand = new Random();

		for (int i = 0; i < A.length; i++) {
			int randomIndexToSwap = rand.nextInt(A.length);
			int temp = A[randomIndexToSwap];
			A[randomIndexToSwap] = A[i];
			A[i] = temp;
		}
	}

	public void printArray() {
		if (A.length == 0) return;

		System.out.print(0);
		for (int i = 1; i < A.length; i++) {
			System.out.print("\t" + i);
		}
		System.out.println();

		System.out.print(A[0]);
		for (int i = 1; i < A.length; i++) {
			System.out.print("\t" + A[i]);
		}
		System.out.println();
	}

	private class BubbleSort extends JPanel implements ActionListener {

		private Timer timer;

		public void paintComponent(Graphics g) {
			int x = 0;
			for (int i = 0; i < A.length; i++) {
				g.setColor(colors[A[i]]);
				g.fillRect(x, 0, 3, 600);
				x += 3;
			}
		}

		public void sort() {
			i = A.length-1;
			j = 0;
			timer = new Timer(1, this);
			timer.start();
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			numComps++;
			compsLabel.setText("Total Comparisons: " + numComps);

			if (A[j] > A[j+1]) {
				int temp = A[j];
				A[j] = A[j+1];
				A[j+1] = temp;

				numSwaps++;
				swapsLabel.setText("Total Swaps: " + numSwaps);
			}

			if (j < i-1) {
				j++;
			}
			else {
				if (i > 1) {
					i--;
					j = 0;
				}
				else {
					timer.stop();
				}
			}

			graphics.repaint();
		}
	}

	private class SelectionSort extends JPanel implements ActionListener {

		private Timer timer;

		public void paintComponent(Graphics g) {
			int x = 0;
			for (int i = 0; i < A.length; i++) {
				g.setColor(colors[A[i]]);
				g.fillRect(x, 0, 3, 600);
				x += 3;
			}
		}

		public void sort() {
			i = A.length-1;
			j = 1;
			k = 0;
			timer = new Timer(1, this);
			timer.start();
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			numComps++;
			compsLabel.setText("Total Comparisons: " + numComps);
			if (A[j] > A[k]) {
				k = j;
			}

			if (j < i) {
				j++;
			}
			else {
				int temp = A[k];
				A[k] = A[i];
				A[i] = temp;
				numSwaps++;
				swapsLabel.setText("Total Swaps: " + numSwaps);

				if (i > 1) {
					i--;
					k = 0;
					j = 1;
				}
				else {
					timer.stop();
				}
			}

			graphics.repaint();
		}
	}

	private class InsertionSort extends JPanel implements ActionListener {

		private Timer timer;
		private String nextStep;
		private int key;

		public void paintComponent(Graphics g) {
			int x = 0;
			for (int i = 0; i < A.length; i++) {
				g.setColor(colors[A[i]]);
				g.fillRect(x, 0, 3, 600);
				x += 3;
			}
		}

		public void sort() {
			j = 1;
			i = j-1;
			key = A[j];
			nextStep = "before";
			timer = new Timer(1, this);
			timer.start();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
	
			if (nextStep.equals("before")) {
				key = A[j];
				i = j-1;
				nextStep = "while";
			}
			
			if (nextStep.equals("while")) {
				numComps++;
				compsLabel.setText("Total Comparisons: " + numComps);
				if (i >= 0 && A[i] > key) {
					A[i+1] = A[i];
					i = i-1;
					nextStep = "while";
				}
				else {
					nextStep = "after";
				}
			}
			else {
				A[i+1] = key;
				
				if (j < A.length-1) {
					j++;
					nextStep = "before";
				}
				else {
					timer.stop();
				}
			}
			graphics.repaint();
		}
	}

	/*
	private class FastInsertionSort extends JPanel implements ActionListener {

		private Timer timer;

		public void paintComponent(Graphics g) {
			int x = 0;
			for (int i = 0; i < A.length; i++) {
				g.setColor(colors[A[i]]);
				g.fillRect(x, 0, 3, 600);
				x += 3;
			}
		}

		public void sort() {
			j = 1;
			timer = new Timer(1, this);
			timer.start();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
	
			int key = A[j];
			int i = j-1;
			
			while (i >= 0 && A[i] > key) {
				A[i+1] = A[i];
				numSwaps++;
				swapsLabel.setText("Total Swaps: " + numSwaps);
				i = i-1;
				numComps++;
				compsLabel.setText("Total Comparisons: " + numComps);
			}
			
			if (i >= 0) {
				numComps++;
				compsLabel.setText("Total Comparisons: " + numComps);
			}
			
			A[i+1] = key;
			numSwaps++;
			swapsLabel.setText("Total Swaps: " + numSwaps);
			
			if (j < A.length-1) {
				j++;
			}
			else {
				timer.stop();
			}
			graphics.repaint();
		}
	}

	*/
	
	/*
	private class MergeSort extends JPanel implements ActionListener {

		private Timer timer;
		private int start;
		private int mid;
		private int end;

		public void paintComponent(Graphics g) {
			int x = 0;
			for (int i = 0; i < A.length; i++) {
				g.setColor(colors[A[i]]);
				g.fillRect(x, 0, 3, 600);
				x += 3;
			}
		}

		public void sort() {
			timer = new Timer(1, this);
			timer.start();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			mergeSort(0, A.length-1);
		}

		public void mergeSort(int l, int r) {
			if (l < r) {
				int m = l + (r-l) / 2;

				mergeSort(l, m);
				graphics.repaint();
				mergeSort(m+1, r);
				graphics.repaint();
				merge(l, m, r);
			}
		}

		public void merge(int start, int mid, int end) {
			int start2 = mid + 1;

			if (A[mid] <= A[start2]) {
				return;
			}

			while (start <= mid && start2 <= end) {
				if (A[start] <= A[start2]) {
					start++;
				}
				else {
					int value = A[start2];
					int index = start2;

					while (index != start) {
						A[index] = A[index - 1];
						index--;
					}
					A[start] = value;

					start++;
					mid++;
					start2++;
				}
			}
		}

	}
	 */

	public static void main(String[] args) {
		new Visualizer();
	}

}
