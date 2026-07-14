# Mortgage Calculator

A beautiful and modern web-based mortgage calculator that helps you estimate your monthly mortgage payments, including principal, interest, property taxes, home insurance, and PMI.

## Features

- **Comprehensive Calculations**: Calculate monthly payments including:
  - Principal & Interest
  - Property Tax
  - Home Insurance
  - PMI (Private Mortgage Insurance)
  
- **Real-time Updates**: Down payment percentage updates automatically as you adjust values

- **Detailed Breakdown**: View a complete breakdown of your monthly payment and total costs

- **Modern UI**: Beautiful, responsive design that works on all devices

- **User-friendly**: Pre-filled with reasonable defaults for quick calculations

## How to Use

1. Open `index.html` in your web browser
2. Enter your loan details:
   - **Home Price**: The total price of the home
   - **Down Payment**: Amount you'll pay upfront
   - **Loan Term**: Length of the mortgage in years (typically 15 or 30)
   - **Interest Rate**: Annual interest rate as a percentage
   - **Property Tax**: Annual property tax amount (optional)
   - **Home Insurance**: Annual home insurance cost (optional)
   - **PMI**: Monthly private mortgage insurance (optional)
3. Click "Calculate Payment" to see your results

## Understanding the Results

### Monthly Payment
Your total monthly payment combining all costs.

### Breakdown
- **Principal & Interest**: The core mortgage payment
- **Property Tax**: Monthly portion of annual property tax
- **Home Insurance**: Monthly portion of annual insurance
- **PMI**: Private mortgage insurance (typically required if down payment < 20%)

### Summary
- **Loan Amount**: Total amount borrowed (Home Price - Down Payment)
- **Total Payment**: Total amount you'll pay over the life of the loan
- **Total Interest**: Total interest paid over the loan term

## Mortgage Calculation Formula

The calculator uses the standard mortgage payment formula:

```
M = P [ r(1+r)^n ] / [ (1+r)^n - 1 ]
```

Where:
- M = Monthly payment (principal + interest)
- P = Principal loan amount
- r = Monthly interest rate (annual rate / 12)
- n = Number of payments (loan term in years × 12)

## Files

- `index.html` - Main HTML structure
- `styles.css` - Modern CSS styling
- `calculator.js` - Mortgage calculation logic

## Browser Compatibility

Works on all modern browsers including:
- Chrome
- Firefox
- Safari
- Edge

## Installation

No installation required! Simply open the `index.html` file in your web browser.

For development or hosting:

```bash
# Serve locally with Python
python -m http.server 8000

# Or with Node.js
npx serve
```

Then open `http://localhost:8000` in your browser.

## Customization

You can easily customize the calculator by modifying:
- Colors in `styles.css` (see CSS variables in `:root`)
- Default values in `index.html`
- Calculation logic in `calculator.js`

## License

Free to use and modify for personal or commercial purposes.
