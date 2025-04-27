from flask import Flask, request, jsonify
from flask_cors import CORS  # To allow cross-origin requests (important for testing with HTML)

# Initialize the Flask app
app = Flask(__name__)
CORS(app)  # Enable CORS for all routes

# Conversion function
def miles_to_km(miles):
    return miles * 1.60934  # 1 mile = 1.60934 kilometers

@app.route('/convert', methods=['GET'])
def convert_miles_to_km():
    # Retrieve miles from query parameter
    try:
        miles = float(request.args.get('miles'))
        kilometers = miles_to_km(miles)
        return jsonify({'kilometers': kilometers})
    except (TypeError, ValueError):
        return jsonify({'error': 'Invalid input. Please enter a valid number for miles.'}), 400

# Run the Flask app
if __name__ == '__main__':
    app.run(debug=True)

