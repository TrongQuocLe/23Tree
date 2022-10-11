import java.util.ArrayList;


public class Tree {
	private Node root;

	// Constructor
	public Tree() {
		root = null;
	}

	public void insert(Integer x) {
		Node newNode = new Node(x);
		if (root == null) {
			root = newNode;
		} else {
			root.addNode(newNode);
		}
	}

	public int size() {
		return root.sizeNode();
	}

	public int size(Integer x) {
		if (find(x, root) == null)
			return 0;
		return find(x, root).sizeNode();
	}

	public Node find(Integer x, Node current) {
//		if (current == null) {
//			return null;
//		}
//		for (int i = 0; i<3; i++) {
//			if (x == current.keys[i]) {
//				return current;
//			}
//		}
//		if ( current.hasChilds() == true) 
//			for (int i = 0; i<3; i++) {
//				if (current.childs[i] != null) {
//					find(x,current.childs[i]);
//					current.childs[i].parent = current;
//				}
//			}
//		return null;
		Node xNode = new Node(x);
		int position = current.compare(xNode);
		if (position == 3)
			return current;
		if (current.childs.get(position) != null) {
			return find(x, current.childs.get(position));
		}
		return null;
	}

	public int get(int x) {
		return 0;
	}

	class Node {

		public ArrayList<Node> childs = new ArrayList();	// 4 childs max
		public ArrayList<Integer> keys = new ArrayList();   // 3 keys max
		public Node parent;

		public Node(Integer x) {
			keys.add(x);
		}

		public int compare(Node newNode) {
			// 0 : smaller than first key / go left
			// 3 : duplicate -> do nothing
			// 1 : bigger than first key and smaller than second key / go center
			// 2 : bigger than others / go right
			int comp = 3;
			if (newNode.keys.get(0) == keys.get(0)) {
				comp = 3;
			} else if (keys.get(2) == null) {
				if (newNode.keys.get(0) < keys.get(0)) {
					comp = 0;
				} else if (newNode.keys.get(0) > keys.get(0)) {
					comp = 2;
				}
			} else {
				if (newNode.keys.get(0) < keys.get(0)) {
					comp = 0;
				} else if (keys.get(0) < newNode.keys.get(0) && newNode.keys.get(0) < keys.get(2)) {
					comp = 1;
				} else if (newNode.keys.get(0) > keys.get(2)) {
					comp = 2;
				}

			}
			return comp;
		}

		public void addNode(Node newNode) {

			int position = compare(newNode);

			if (!hasChilds() && position != 3) {
				if (keys.get(position) != null) {
					switch (position) {
					case 0:
						if (keys.get(2) == null) {
							keys.get(2) = keys.get(0);
							keys.get(0) = newNode.keys.get(0);
						} else {
							keys.get(0) = keys.get(0);
							keys[0] = newNode.keys[0];
						}
						break;
					case 2:
						keys[1] = keys[2];
						keys[2] = newNode.keys[0];
						break;
					}
				} else {
					keys[position] = newNode.keys[0];
				}
			} else {
				if (childs[position] == null) {
					childs[position] = newNode;
				} else {
					childs[position].addNode(newNode);
					childs[position].parent = this;
				}
			}
			if (numberOfKeys() == 3) {
				split();
			}
		}

		public void split() {
			if (parent == null) {
				for (int i = 0; i < 3; i += 2) {
					childs[i] = new Node(keys[i]);
					childs[i].parent = this;
					keys[i] = null;
				}
				keys[0] = keys[1];
				keys[1] = null;
				childs[1] = null; // delete center
			} else {
				if (parent.keys[2] == null) {
					int comp = parent.keys[0].compareTo(keys[1]);
					if (comp < 0) {
						parent.keys[2] = keys[1];
						// add first and third node to parent
						parent.addNode(new Node(keys[0]));
						keys[0] = keys[2];
						keys[1] = null;
						keys[2] = null;
					} else if (comp > 0) {
						parent.keys[2] = parent.keys[0];
						parent.keys[0] = keys[1];
						// add first and third node to parent
						parent.addNode(new Node(keys[2]));
						keys[1] = null;
						keys[2] = null;
					}

				} else {
					Node newNode = new Node(keys[1]);
					int position = parent.compare(newNode);

					if (parent.keys[position] != null) {
						switch (position) {
						case 0:
							parent.keys[1] = parent.keys[0];
							parent.keys[0] = newNode.keys[0];
							keys[1] = null;
							Node keys2Node = new Node(keys[2]);
							keys[2] = null;
							Node centerNode = parent.childs[1];
							parent.split();
							parent.addNode(keys2Node);
							for (int i = 0; i<3; i++) {
								if (centerNode.keys[i] != null ) {
									parent.addNode(new Node(centerNode.keys[i]));
								}
							}
							break;
						case 2:
							parent.keys[1] = parent.keys[2];
							parent.keys[2] = newNode.keys[0];
							keys[1] = null;
							Node keys1Node = new Node(keys[1]);
							keys[1] = null;
							parent.split();
							parent.addNode(keys1Node);
							break;
						}
					} else {
						parent.keys[position] = newNode.keys[0];
					}
//					if (numberOfKeys() == 3) {
//						split();
//					}

				}

			}

		}

		public boolean hasChilds() {
			for (int i = 0; i < 3; i++) {
				if (childs[i] != null)
					return true;
			}
			return false;

		}

		public int sizeNode() {
			// return the size of node
			int size = this.numberOfKeys();
			for (int i = 0; i < 3; i++) {
				if (childs[i] != null)
					size = size + this.childs[i].sizeNode();
			}
			return size;

		}

		public int numberOfKeys() {
			// return the number of key in a node
			int count = 0;
			for (int i = 0; i < 3; i++)
				if (keys[i] != null)
					count++;
			return count;
		}

	}

	public static void main(String[] arg) {
		Tree t = new Tree();
		t.insert(1);
		t.insert(9);
		t.insert(15);
		t.insert(13);
		t.insert(20);
		t.insert(7);
		t.insert(4);
//		t.insert(1);
		System.out.println(t.size());

	}

}
