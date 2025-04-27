from flask import Flask, request, render_template

app = Flask(__name__)

# Helper function for formatted responses
def create_response(operation, a, b, result):
    return f"""
    <html>
    <head>
        <title>Simple Calculator</title>
        <style>
            body {{ font-family: Arial, sans-serif; text-align: center; background-color: #f0f8ff; padding: 20px; }}
            h1 {{ color: #333366; }}
            .result {{ font-size: 20px; font-weight: bold; color: #006400; }}
            .error {{ color: red; font-size: 18px; font-weight: bold; }}
        </style>
    </head>
    <body>
        <h1>Calculator Result</h1>
        <p>The result of {operation} {a} and {b} is:</p>
        <p class="result">{result}</p>
        <br>
        <a href="/">Back to Calculator</a>
    </body>
    </html>
    """

# Addition operation
@app.route('/add', methods=['GET'])
def add():
    a = float(request.args.get('a'))
    b = float(request.args.get('b'))
    result = a + b
    return create_response("adding", a, b, result)

# Subtraction operation
@app.route('/subtract', methods=['GET'])
def subtract():
    a = float(request.args.get('a'))
    b = float(request.args.get('b'))
    result = a - b
    return create_response("subtracting", a, b, result)

# Multiplication operation
@app.route('/multiply', methods=['GET'])
def multiply():
    a = float(request.args.get('a'))
    b = float(request.args.get('b'))
    result = a * b
    return create_response("multiplying", a, b, result)

# Division operation
@app.route('/divide', methods=['GET'])
def divide():
    a = float(request.args.get('a'))
    b = float(request.args.get('b'))
    if b == 0:
        return f'<html><body><h1 class="error">Error: Cannot divide by zero!</h1><a href="/">Back to Calculator</a></body></html>', 400
    result = a / b
    return create_response("dividing", a, b, result)

# Handle favicon.ico to prevent 404 errors
@app.route('/favicon.ico')
def favicon():
    return '', 204

# Homepage for the calculator
@app.route('/')
def home():
    return render_template('index.html')

if __name__ == '__main__':
    app.run(debug=True)

