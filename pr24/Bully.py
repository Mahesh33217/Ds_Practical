import socket
import time
import threading
import sys

# Default settings
process_ports = {1: 5001, 2: 5002, 3: 5003}
leader = None
election_in_progress = False

# Get process ID from command-line argument
process_id = int(sys.argv[1])  # Process ID passed when running the script

# Send message to a target process
def send_message(target_id, message):
    target_port = process_ports[target_id]
    try:
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
            s.connect(('localhost', target_port))  # Connect to localhost (or use an IP address for remote machines)
            s.sendall(message.encode())
            print(f"Process {process_id} sent message to Process {target_id}: {message}")
    except Exception as e:
        print(f"Error sending message to Process {target_id}: {e}")
        time.sleep(1)

# Handle incoming connections from other processes
def handle_incoming_connections():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind(('localhost', process_ports[process_id]))  # Bind to the current process port
        s.listen(5)
        print(f"Process {process_id} is listening on port {process_ports[process_id]}...")

        while True:
            conn, addr = s.accept()
            with conn:
                message = conn.recv(1024).decode()
                if message == "election":
                    print(f"Process {process_id} received 'election' message")
                    handle_election()
                elif message == "OK":
                    print(f"Process {process_id} received 'OK' from higher ID process")
                    handle_ok()
                elif message == "leader":
                    print(f"Process {process_id} received 'leader' message")
                    handle_leader_message()

# Start the election process
def start_election():
    global leader, election_in_progress
    if election_in_progress:
        return
    election_in_progress = True
    print(f"Process {process_id} is starting the election process.")
    
    for p_id in range(process_id + 1, 4):  # Send election to higher ID processes
        send_message(p_id, "election")
    
    time.sleep(1)  # Ensure some delay for election responses
    if leader is None:  # No leader is elected, this process becomes the leader
        print(f"Process {process_id} becomes the leader!")
        leader = process_id
        for p_id in range(1, 4):  # Send leader message to all processes
            send_message(p_id, "leader")
    election_in_progress = False

def handle_election():
    if process_id > 1:  # Process 1 is always the lowest, no need to send "OK" for itself
        send_message(1, "OK")  # Respond to higher ID process with OK

def handle_ok():
    pass

def handle_leader_message():
    global leader
    leader = process_id  # Update leader info when a leader message is received

# Main function to run the process
def main():
    print(f"Starting Process {process_id}...")
    threading.Thread(target=handle_incoming_connections, daemon=True).start()  # Start the thread for handling incoming connections
    time.sleep(1)  # Give the thread time to set up listening
    start_election()  # Start the election process for the process

if __name__ == '__main__':
    if process_id not in process_ports:
        print("Invalid process ID. Please use 1, 2, or 3.")
        sys.exit(1)
    main()

