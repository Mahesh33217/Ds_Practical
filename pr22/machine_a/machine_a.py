import socket
import time

# IP address and port for Machine A
server_host = '192.168.20.82'  # IP of Machine A
server_port = 12345  # Port to listen on

# Function to create the server (Machine A)
def server():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((server_host, server_port))  # Bind to IP and port
    server_socket.listen(1)
    print(f"Machine A is waiting for a connection on {server_host}:{server_port}...")
    
    # Accept the connection from Machine B
    client_socket, client_address = server_socket.accept()
    print(f"Connection established with {client_address}")

    try:
        while True:
            # Wait for the token from Machine B
            token = client_socket.recv(1024).decode()
            if token == 'token':
                print("Machine A receives the token.")
                # Enter critical section
                print("Machine A entering critical section.")
                time.sleep(2)  # Simulate critical section work
                print("Machine A exiting critical section.")
                
                # Pass the token to Machine B
                print("Machine A passes the token to Machine B.")
                client_socket.sendall(b'token')
    finally:
        client_socket.close()

if __name__ == "__main__":
    server()

