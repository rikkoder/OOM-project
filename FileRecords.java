// importing awt class  
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class FileRecords implements FocusListener {
	List nameList;
	Label showInfo, showName, showRoll, showSec;
	String t_stuname;

	// constructor
	FileRecords() {

		// creating the frame  
		Frame f = new Frame();
		// creating the list of 5 rows   
		nameList = new List(5);

		// setting the position of list component   
		nameList.setBounds(30, 100, 175, 450);

		// adding the list to frame  
		f.add(nameList);
		// for data display
		showName = new Label();
		showRoll = new Label();
		showSec = new Label();
		showInfo = new Label("STUDENT DETAILS");

		showInfo.setBounds(290, 150, 500, 100);
		showName.setBounds(220, 250, 500, 50);
		showRoll.setBounds(220, 300, 200, 50);
		showSec.setBounds(220, 350, 200, 50);

		Font resultFont = new Font("Times", Font.BOLD, 15);
		Font resultFont1 = new Font("Dialog", Font.BOLD, 19);
		showInfo.setFont(resultFont1);
		showName.setFont(resultFont);
		showRoll.setFont(resultFont);
		showSec.setFont(resultFont);
		f.add(showName);
		f.add(showRoll);
		f.add(showSec);
		f.add(showInfo);

		// initializing list
		updateList();

		//Text Field
		TextField t1 = new TextField("Search");
		t1.addFocusListener(this);

		t1.setBounds(30, 50, 175, 30);
		f.add(t1);

		// buttons
		// search button
		Button btnSearch = new Button("Search");
		btnSearch.setBounds(210, 50, 83, 30);
		f.add(btnSearch);

		// add button
		Button btnShow = new Button("Show");
		btnShow.setBounds(303, 50, 83, 30);
		f.add(btnShow);

		// delete button
		Button btnDel = new Button("Delete");
		btnDel.setBounds(396, 50, 83, 30);
		f.add(btnDel);

		// modify button
		Button btnAdd = new Button("Add");
		btnAdd.setBounds(489, 50, 83, 30);
		f.add(btnAdd);

		// list title
		Font myFont = new Font("SansSerif", Font.BOLD, 12);
		Label listTitle = new Label("List : ");
		listTitle.setFont(myFont);
		listTitle.setBounds(30, 40, 200, 10);
		f.add(listTitle);

		// add title
		f.setTitle("IT Department File Records");

		//Getting the selected item name from the string : 
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String obj = nameList.getItem(nameList.getSelectedIndex());
				String[] sarr = show_func(nameList.getItem(nameList.getSelectedIndex()));
				showName.setText("Student Name : " + sarr[0]);
				showRoll.setText("Roll Number: " + sarr[1]);
				showSec.setText("Section : " + sarr[2]);
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = "Search  Clicked ";
				System.out.println(t_stuname);
				String[] sarr = show_func(t_stuname);
				{
					showName.setText("Student Name : " + sarr[0]);
					showRoll.setText("Roll Number: " + sarr[1]);
					showSec.setText("Section : " + sarr[2]);
				}
			}
		});
		// adding WindowListener to the Frame  
		// and using the windowClosing() method of WindowAdapter class  

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});

		//Adding Functionality to Dialog Box    
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Label lname = new Label("Enter Name "), lsection = new Label("Enter Section"), lroll = new Label("Enter Roll Number"), ladded = new Label("Record Added ");

				TextField tname = new TextField("Here"), tsection = new TextField("Here"), troll = new TextField("Here");

				lname.setBounds(20, 30, 200, 30);
				tname.setBounds(20, 70, 200, 30);
				lsection.setBounds(20, 110, 200, 30);
				tsection.setBounds(20, 150, 200, 30);
				lroll.setBounds(20, 190, 200, 30);
				troll.setBounds(20, 230, 200, 30);
				ladded.setBounds(20, 270, 200, 30);
				ladded.setVisible(false);
				Dialog d = new Dialog(f, "Add new record ", true);
				Button b = new Button("Close");
				Button b1 = new Button("Add ");
				b.setBounds(200, 300, 75, 40);
				b1.setBounds(120, 300, 75, 40);
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						d.setVisible(false);
					}
				});
				b1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String sa[] = new String[3];
						sa[0] = tname.getText().toUpperCase();
						sa[1] = troll.getText().toUpperCase();
						sa[2] = tsection.getText().toUpperCase();
						write_funcn(sa);
						ladded.setVisible(true);

						updateList();
					}
				});
				d.add(ladded);
				d.add(lname);
				d.add(lsection);
				d.add(lroll);
				d.add(tname);
				d.add(tsection);
				d.add(troll);
				d.add(b);
				d.add(b1);
				d.setLayout(null);
				d.setSize(400, 400);
				d.setVisible(true);
				d.setResizable(false);
			}
		});

		// Delete Dialog
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Label lname = new Label("Enter Name "), lroll = new Label("Enter Roll Number"), msg = new Label();

				TextField tname = new TextField("Here"), troll = new TextField("Here");

				lname.setBounds(20, 50, 200, 30);
				tname.setBounds(20, 90, 200, 30);
				lroll.setBounds(20, 140, 200, 30);
				troll.setBounds(20, 180, 200, 30);
				msg.setBounds(20, 220, 250, 30);
				msg.setVisible(false);

				Dialog d = new Dialog(f, "Delete record ", true);
				Button cls = new Button("Close");
				Button del = new Button("Delete ");
				cls.setBounds(200, 300, 75, 40);
				del.setBounds(120, 300, 75, 40);
				cls.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						d.setVisible(false);
					}
				});
				del.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = tname.getText(), roll = troll.getText();
						name = name.toUpperCase();
						boolean success = deleteEntry(name, roll);
						if (success) {
							updateList();
							msg.setText("Entry Deleted Successfully !");
						} else {
							msg.setText("Deletion Failed !\n");
						}

						msg.setVisible(true);
					}
				});

				d.add(msg);
				d.add(lname);
				d.add(lroll);
				d.add(tname);
				d.add(troll);
				d.add(cls);
				d.add(del);
				d.setLayout(null);
				d.setSize(400, 400);
				d.setVisible(true);
				d.setResizable(false);
			}
		});

		// unresizable
		f.setResizable(false);

		// setting size, layout and visibility of frame  
		f.setSize(600, 600);
		f.setLayout(null);
		f.setVisible(true);
	}

	String[] reading_funcn(String fileName) {
		int i = 0, j = 0;
		try {
			File file = new File(fileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while (br.readLine() != null) {
				i++;
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] ret = new String[i];
		try {
			File file = new File(fileName);
			FileReader fr = new FileReader(file);
			BufferedReader br2 = new BufferedReader(fr);
			String name;
			while ((name = br2.readLine()) != null) {
				if (!name.isEmpty()) {
					ret[j] = name;
					j++;
				}
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	String[] show_func(String search) {
		String[] res = new String[3];

		int i = 0, j = 0;
		try {
			search = search.toUpperCase();
			File file = new File("Student_Name.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String Name;
			while ((Name = br.readLine()) != null) {
				if (Name.equals(search) || i != 0) {
					res[j] = Name;
					i++;
					j++;
					if (j == 3) {
						i = 0;
					}
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	void write_funcn(String sarr[]) {
		try {
			File file = new File("Student_Name.txt");
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter pr = new BufferedWriter(fw);
			pr.write(sarr[0]);
			pr.write("\n");
			pr.write(sarr[1]);
			pr.write("\n");
			pr.write(sarr[2]);
			pr.write("\n");
			pr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	int search_funcn(String search) {
		int i = 0;
		try {
			search = search.toUpperCase();
			File file = new File("Student_Name.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String Name;
			while ((Name = br.readLine()) != null) {
				if (Name.equals(search)) {
					i++;
					break;
				}
			}
			br.close();
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return i;
	}

	// update list
	void updateList() {
		// clearing list
		nameList.removeAll();

		// clearing result output
		showName.setText("");
		showRoll.setText("");
		showSec.setText("");

		// adding names into the list 
		String[] sarr = reading_funcn("Student_Name.txt");
		for (int i = 0; i < sarr.length; i += 3)
			nameList.add(sarr[i]);
	} /* end of updateList */

	// search roll no and name and return index
	int searchRoll(String name, String roll) {
		File file;
		FileReader fr;
		BufferedReader br;
		int ind = -1;

		try {
			file = new File("Student_Name.txt");
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (line.equals(name)) {
					if ((line = br.readLine()) != null && line.equals(roll)) {
						ind = i;
						break;
					}
				}
				i++;
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ind;
	} /* end of searchRoll */

	// function to delete entry
	boolean deleteEntry(String name, String roll) {
		int ind = searchRoll(name, roll);
		if (ind == -1)
			return false;

		int i = 0;

		File inputFile, tempFile;
		BufferedReader reader;
		BufferedWriter writer;

		try {
			inputFile = new File("Student_Name.txt");
			tempFile = new File("myTempFile.txt");

			reader = new BufferedReader(new FileReader(inputFile));
			writer = new BufferedWriter(new FileWriter(tempFile));

			String currentLine;

			while ((currentLine = reader.readLine()) != null) {
				// skipping data for deleted entry
				if (i < ind || i > ind + 2)
					writer.write(currentLine + '\n');
				i++;
			}
			writer.close();
			reader.close();
			boolean successful = tempFile.renameTo(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	} /* end of deleteEntry */


	public void focusGained(FocusEvent fe) {
		// Get what textfield got focus
		TextField t = (TextField) fe.getSource();
		t.setText("");
	}

	public void focusLost(FocusEvent fe) {
		// Get what textfield lost focus
		TextField t = (TextField) fe.getSource();
		t_stuname = t.getText();
		t.setText("Search");
	}

	// main method  
	public static void main(String args[]) {
		new FileRecords();
	}
}
