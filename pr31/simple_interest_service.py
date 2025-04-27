from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/calculate_interest', methods=['GET'])
def calculate_interest():
    # Get the input values from query parameters
    principal = float(request.args.get('principal'))
    rate = float(request.args.get('rate'))
    time = float(request.args.get('time'))
    
    # Simple Interest formula: SI = (P * R * T) / 100
    simple_interest = (principal * rate * time) / 100

    # Returning the result as JSON
    return jsonify({
        'principal': principal,
        'rate': rate,
        'time': time,
        'simple_interest': simple_interest
    })

# Run the Flask app
if __name__ == '__main__':
    app.run(debug=True)

