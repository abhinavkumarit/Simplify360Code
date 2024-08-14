package Simplify360;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class FriendNetwork {
	 Map<String, Set<String>> friendMap = new HashMap<>();

	    // Add a friend connection between two people
	    void addFriend(String person, String friend) {
	        if (!friendMap.containsKey(person)) {
	            friendMap.put(person, new HashSet<String>());
	        }
	        if (!friendMap.containsKey(friend)) {
	            friendMap.put(friend, new HashSet<String>());
	        }
	        friendMap.get(person).add(friend);
	        friendMap.get(friend).add(person);
	    }

	    // Get all friends of a person
	    Set<String> getFriends(String person) {
	        Set<String> friends = friendMap.get(person);
	        if (friends == null) {
	            return new HashSet<String>();
	        }
	        return friends;
	    }

	    // Get common friends between two people
	    Set<String> getCommonFriends(String person1, String person2) {
	        Set<String> friends1 = getFriends(person1);
	        Set<String> friends2 = getFriends(person2);
	        
	        Set<String> commonFriends = new HashSet<String>();
	        for (String friend : friends1) {
	            if (friends2.contains(friend)) {
	                commonFriends.add(friend);
	            }
	        }
	        return commonFriends;
	    }

	    // Find nth connection between two people
	    int findNthConnection(String start, String target) {
	        if (!friendMap.containsKey(start) || !friendMap.containsKey(target)) {
	            return -1; // No connection
	        }

	        Queue<String> queue = new LinkedList<String>();
	        Set<String> visited = new HashSet<String>();
	        Map<String, Integer> connectionLevel = new HashMap<String, Integer>();

	        queue.add(start);
	        visited.add(start);
	        connectionLevel.put(start, 1);

	        while (!queue.isEmpty()) {
	            String current = queue.poll();
	            int level = connectionLevel.get(current);

	            Set<String> friends = getFriends(current);
	            for (String friend : friends) {
	                if (!visited.contains(friend)) {
	                    visited.add(friend);
	                    connectionLevel.put(friend, level + 1);
	                    queue.add(friend);
	                    if (friend.equals(target)) {
	                        return level + 1;
	                    }
	                }
	            }
	        }

	        return -1; // No connection found
	    }

	    public static void main(String[] args) {
	        FriendNetwork network = new FriendNetwork();

	        // Adding friends
	        network.addFriend("Alice", "Bob");
	        network.addFriend("Bob", "Charlie");
	        network.addFriend("Alice", "David");
	        network.addFriend("David", "Eve");
	        network.addFriend("Charlie", "Frank");
	        network.addFriend("Bob", "Janice");

	        // Get all friends of Alice and Bob
	        System.out.println("Alice's friends: " + network.getFriends("Alice"));
	        System.out.println("Bob's friends: " + network.getFriends("Bob"));

	        // Get common friends between Alice and Bob
	        System.out.println("Common friends between Alice and Bob: " + network.getCommonFriends("Alice", "Bob"));

	        // Find nth connection between Alice and Janice
	        System.out.println("Nth connection between Alice and Janice: " + network.findNthConnection("Alice", "Janice"));

	        // Find nth connection between Alice and Frank
	        System.out.println("Nth connection between Alice and Frank: " + network.findNthConnection("Alice", "Frank"));
			
			//Time Complexity

			//1. addFriend: O(1)
			//2. getFriends: O(1)
			//3. getCommonFriends: O(F1) where F1 is the number of friends for one of the people.
			//4. findNthConnection: O(V + F) where V is the number of people and F is the number of friend connections.

			//Space Complexity
			//1. friendMap: O(V + F)
            //2. getCommonFriends: O(min(F1, F2))
            //3. findNthConnection: O(V)
		
		}
	}