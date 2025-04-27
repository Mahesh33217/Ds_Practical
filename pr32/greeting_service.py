from flask import Flask, request, jsonify
from flask_cors import CORS  # Allow cross-origin requests (important for testing with HTML)

# Initialize the Flask app
app = Flask(__name__)
CORS(app)  # Enable CORS for all routes

@app.route('/greet', methods=['GET'])
def greet_user():
    # Retrieve the user's name from the query parameter 'name'
    name = request.args.get('name', default="Guest", type=str)
    
    # Create the greeting message
    greeting_message = f"Hello, {name}!"
    
    # Return the greeting message in JSON format
    return jsonify({'greeting': greeting_message})

# Run the Flask app
if __name__ == '__main__':
    app.run(debug=True)

