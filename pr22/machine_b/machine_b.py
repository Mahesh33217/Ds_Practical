import socket
import time

# IP address and port of Machine A
server_host = '192.168.20.82'  # IP of Machine A
server_port = 12345  # Port to connect to

# Function to create the client (Machine B)
def client():
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect((server_host, server_port))  # Connect to Machine A
    print(f"Machine B is connected to {server_host}:{server_port}")

    try:
        while True:
            # Pass the token to Machine A initially
            client_socket.sendall(b'token')
            print("Machine B passes the token to Machine A.")
            
            # Wait for the token from Machine A
            token = client_socket.recv(1024).decode()
            if token == 'token':
                print("Machine B receives the token.")
                # Enter critical section
                print("Machine B entering critical section.")
                time.sleep(2)  # Simulate critical section work
                print("Machine B exiting critical section.")
                
                # Pass the token back to Machine A
                print("Machine B passes the token to Machine A.")
                client_socket.sendall(b'token')
    finally:
        client_socket.close()

if __name__ == "__main__":
    client()

