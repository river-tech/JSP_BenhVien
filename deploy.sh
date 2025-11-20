#!/bin/zsh

# ===============================================
# 🚀 JSP/Servlet Auto Deploy Script for Tomcat 10
# ===============================================

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
APP_NAME="benhvien-web"
TOMCAT_HOME="$HOME/sentiment-tomcat"
WAR_FILE="${SCRIPT_DIR}/target/${APP_NAME}-1.0.0.war"
DEFAULT_JAVA_HOME="/Library/Java/JavaVirtualMachines/temurin-11.jdk/Contents/Home"

# ===============================================
# ☕ Java setup (JDK 11 required)
# ===============================================
echo ""
echo "======================================"
echo "☕ Checking JAVA_HOME (using JDK 11)"
echo "======================================"

if [ -d "${DEFAULT_JAVA_HOME}" ]; then
  export JAVA_HOME="${DEFAULT_JAVA_HOME}"
  export PATH="${JAVA_HOME}/bin:${PATH}"
else
  echo "⚠️  JDK 11 not found at ${DEFAULT_JAVA_HOME}"
  echo "Using system default Java"
fi

echo "Using $(java -version 2>&1 | head -n 1)"

# ===============================================
# 🚀 Build WAR
# ===============================================
echo ""
echo "======================================"
echo "🚀 Building WAR package"
echo "======================================"

mvn -DskipTests clean package || {
  echo "❌ Build failed!"
  exit 1
}

# ===============================================
# 🧹 Clean old deployment
# ===============================================
echo ""
echo "======================================"
echo "🧹 Cleaning old WAR in Tomcat..."
echo "======================================"

rm -rf "${TOMCAT_HOME}/webapps/${APP_NAME}" "${TOMCAT_HOME}/webapps/${APP_NAME}-1.0.0.war" "${TOMCAT_HOME}/webapps/${APP_NAME}-1.0.0"

# ===============================================
# 📦 Copy new WAR
# ===============================================
echo ""
echo "======================================"
echo "📦 Deploying new WAR to Tomcat..."
echo "======================================"

if [ ! -f "${WAR_FILE}" ]; then
  echo "❌ WAR file not found: ${WAR_FILE}"
  exit 1
fi

cp "${WAR_FILE}" "${TOMCAT_HOME}/webapps/"

# ===============================================
# 🔁 Restart YOUR Tomcat instance (NOT brew one)
# ===============================================
echo ""
echo "======================================"
echo "🔁 Restarting standalone Tomcat..."
echo "======================================"

# Stop Tomcat riêng
"${TOMCAT_HOME}/bin/shutdown.sh" >/dev/null 2>&1 || true
sleep 2

# Start lại Tomcat riêng
"${TOMCAT_HOME}/bin/startup.sh"

# ===============================================
# 🎉 Output
# ===============================================
echo ""
echo "======================================"
echo "🎉 Deployment Completed!"
echo "🌐 App URL: http://localhost:8080/${APP_NAME}-1.0.0/"
echo "🌐 Or: http://localhost:8080/${APP_NAME}-1.0.0/vaccines/search"
echo "======================================"

