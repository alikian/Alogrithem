document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('mortgageForm');
    const homePriceInput = document.getElementById('homePrice');
    const downPaymentInput = document.getElementById('downPayment');
    const downPaymentPercentSpan = document.getElementById('downPaymentPercent');
    const resultsDiv = document.getElementById('results');

    function updateDownPaymentPercentage() {
        const homePrice = parseFloat(homePriceInput.value) || 0;
        const downPayment = parseFloat(downPaymentInput.value) || 0;
        
        if (homePrice > 0) {
            const percentage = ((downPayment / homePrice) * 100).toFixed(1);
            downPaymentPercentSpan.textContent = `${percentage}%`;
        } else {
            downPaymentPercentSpan.textContent = '0.0%';
        }
    }

    homePriceInput.addEventListener('input', updateDownPaymentPercentage);
    downPaymentInput.addEventListener('input', updateDownPaymentPercentage);

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        calculateMortgage();
    });

    function calculateMortgage() {
        const homePrice = parseFloat(homePriceInput.value) || 0;
        const downPayment = parseFloat(downPaymentInput.value) || 0;
        const loanTerm = parseInt(document.getElementById('loanTerm').value) || 30;
        const interestRate = parseFloat(document.getElementById('interestRate').value) || 0;
        const propertyTax = parseFloat(document.getElementById('propertyTax').value) || 0;
        const homeInsurance = parseFloat(document.getElementById('homeInsurance').value) || 0;
        const pmi = parseFloat(document.getElementById('pmi').value) || 0;

        const loanAmount = homePrice - downPayment;
        
        if (loanAmount <= 0) {
            alert('Loan amount must be greater than 0. Please adjust home price or down payment.');
            return;
        }

        const monthlyInterestRate = (interestRate / 100) / 12;
        const numberOfPayments = loanTerm * 12;

        let principalAndInterest;
        if (monthlyInterestRate === 0) {
            principalAndInterest = loanAmount / numberOfPayments;
        } else {
            principalAndInterest = loanAmount * 
                (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments)) /
                (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
        }

        const monthlyPropertyTax = propertyTax / 12;
        const monthlyInsurance = homeInsurance / 12;
        
        const totalMonthlyPayment = principalAndInterest + monthlyPropertyTax + monthlyInsurance + pmi;

        const totalPayment = (principalAndInterest * numberOfPayments) + (propertyTax * loanTerm) + (homeInsurance * loanTerm) + (pmi * numberOfPayments);
        const totalInterest = (principalAndInterest * numberOfPayments) - loanAmount;

        document.getElementById('monthlyPayment').textContent = formatCurrency(totalMonthlyPayment);
        document.getElementById('principalInterest').textContent = formatCurrency(principalAndInterest);
        document.getElementById('monthlyPropertyTax').textContent = formatCurrency(monthlyPropertyTax);
        document.getElementById('monthlyInsurance').textContent = formatCurrency(monthlyInsurance);
        document.getElementById('monthlyPMI').textContent = formatCurrency(pmi);
        document.getElementById('loanAmount').textContent = formatCurrency(loanAmount);
        document.getElementById('totalPayment').textContent = formatCurrency(totalPayment);
        document.getElementById('totalInterest').textContent = formatCurrency(totalInterest);

        resultsDiv.classList.remove('hidden');
        
        resultsDiv.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
    }

    function formatCurrency(amount) {
        return new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: 'USD',
            minimumFractionDigits: 0,
            maximumFractionDigits: 0
        }).format(amount);
    }

    calculateMortgage();
});
